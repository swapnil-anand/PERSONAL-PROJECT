package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.IsinEntity;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.datafieldsandisin.IsinRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata.CategoryRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata.DataFieldMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata.SearchSubjectRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata.SubjectRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.DebtSecurityMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.SecurityRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.SymbolRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.IsinRequestBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinNoDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.MasterData;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.security.SymbolResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtApplicationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DebtApplicationService implements DebtApplicationServiceInterface {
    private SubjectRepository subjectRepo;
    private CategoryRepository categoryRepo;
    private DataFieldMasterRepository dataFieldMasterRepo;
    private IsinRepository isinRepo;
    private AnnouncementRepository announcementRepo;
    private SearchSubjectRepository searchSubjectRepo;
    private SecurityRepository securityRepo;
    private SymbolRepository symbolRepo;
    private DebtSecurityMasterRepository debtSecurityMas;

    @Autowired
    public DebtApplicationService(SubjectRepository subjectRepo, CategoryRepository categoryRepo, DataFieldMasterRepository dataFieldMasterRepo, IsinRepository isinRepo, AnnouncementRepository announcementRepo, SearchSubjectRepository searchSubjectRepo, SecurityRepository securityRepo, SymbolRepository symbolRepo, DebtSecurityMasterRepository debtSecurityMas) {
        this.subjectRepo = subjectRepo;
        this.categoryRepo = categoryRepo;
        this.dataFieldMasterRepo = dataFieldMasterRepo;
        this.isinRepo = isinRepo;
        this.announcementRepo = announcementRepo;
        this.searchSubjectRepo = searchSubjectRepo;
        this.securityRepo = securityRepo;
        this.symbolRepo = symbolRepo;
        this.debtSecurityMas = debtSecurityMas;
    }

    @Override
    public ResponseEntity<MasterData> getSubjectsAndCategory(String companyType, Map<String,String> header){
        MasterData data = MasterData.builder().build();
        if(companyType.equals("A") || companyType.equals("C") || companyType.equals("B") ) {
            Long companyTypeId = companyType.equals("A") ? 1L : (companyType.equals("B") ? 2L : (companyType.equals("C") ? 3L : 4L));
            data.setSubjects(this.subjectRepo.findByCompanyType(companyTypeId));
            data.setCategory(this.categoryRepo.findByCompanyType(companyTypeId));
        } else if(companyType.equals("NSE")) {
            data.setSubjects(this.subjectRepo.findAllCompanyType());
            data.setCategory(this.categoryRepo.findAllCompanyType());
        }
        List<Long> subjectIds = new ArrayList<>();
        if(data.getSubjects() != null) {
            for (int i = 0; i < data.getSubjects().size(); i++) {
                subjectIds.add(data.getSubjects().get(i).getSubjectId());
            }
        }
        data.setSearchSubject(this.searchSubjectRepo.findAllSearchSubject(subjectIds));
        data.setIsin(this.dataFieldMasterRepo.findByFieldDisplay(Constants.ISIN));
        data.setDataField(this.dataFieldMasterRepo.findByFieldDisplay(Constants.DATA_FIELDS));
        data.setDisclaimer(this.dataFieldMasterRepo.findByFieldDisplay(Constants.DISCLAIMER));
        return new ResponseEntity<> (data, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<IsinResponseBody>> getIsinByAnnouncementId(String announcementUuid, Map<String,String> header){
        return new ResponseEntity<>(
                this.isinRepo.getIsinByAnnouncementId(announcementUuid)
                , HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<Status> saveIsin(String announcementUuid, List<IsinRequestBody> isinRequest, Map<String,String> header){
        Status status = Status.builder().build();
        Announcements announcement = this.announcementRepo.findByAnnId(announcementUuid);
        for (IsinRequestBody isin : isinRequest) {
            IsinEntity newIsinEntity = IsinEntity.builder()
                    .isin(isin.getIsin())
                    .dateOfBuyback(isin.getDateOfBuyback() == null ? null : this.getDateTimeConversion(isin.getDateOfBuyback()))
                    .dueDate(isin.getDueDate() == null ? null : this.getDateTimeConversion(isin.getDueDate()))
                    .natureOfPayment(isin.getNatureOfPayment())
                    .paymentDate(isin.getPaymentDate() == null ? null : this.getDateTimeConversion(isin.getPaymentDate()))
                    .outstandingBonds(isin.getOutstandingBonds())
                    .outstandingIssueSize(isin.getOutstandingIssueSize())
                    .recordDate(isin.getRecordDate() == null ? null : this.getDateTimeConversion(isin.getRecordDate()))
                    .noOfBonds(isin.getNoOfBonds())
                    .announcement(announcement)
                    .build();
            this.isinRepo.save(newIsinEntity);
        }
        status.setStatus("SAVED");
        return ResponseEntity.ok().body(status);
    }

    @Override
    public ResponseEntity<IsinNoDetails> getNeapsIsin(Map<String,String> header){
        IsinNoDetails isinNeaps = IsinNoDetails.builder().build();
        isinNeaps.setPublicIsin(this.securityRepo.findForIsin());
        isinNeaps.setPrivateIsin(this.debtSecurityMas.findForIsin());
        return ResponseEntity.ok()
                .body(isinNeaps);
    }

    private LocalDateTime getDateTimeConversion(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(dateTime, formatter);
    }

    @Override
    public ResponseEntity<List<SymbolResponse>> getAllCompany(Long catId,Map<String,String> header){
        return ResponseEntity.ok().body(this.symbolRepo.getAllCompany(catId));
    }
}
