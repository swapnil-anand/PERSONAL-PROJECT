package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.bookmark.Bookmarks;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.communication.Communications;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.Attachments;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.exceptions.Exceptions;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.IsinEntity;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.CompanyMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.watchlist.Watchlist;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AttachmentRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.bookmark.BookmarkRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.communications.CommunicationRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.datafieldsandisin.IsinRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.exceptions.ExceptionsRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.DebtSecurityMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.FinYearsRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.SecurityRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.watchlist.WatchlistRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.generalreport.GeneralReportRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.watchlist.WatchListRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard.*;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.bookmark.BookmarkRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.*;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtDashboardControllerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DebtDashboardService implements DebtDashboardControllerInterface {
    private static final Logger log = LoggerFactory.getLogger(DebtDashboardService.class);
    private BookmarkRepository bookmarkRepo;
    private AnnouncementRepository announcementRepo;
    private SecurityRepository securityRepo;
    private IsinRepository isinRepo;
    private DebtSecurityMasterRepository debtSecurity;
    private CommunicationRepository communicationRepo;
    private FinYearsRepository finYearsRepo;
    private ExceptionsRepository exceptionRepo;
    private WatchlistRepository watchlistRepo;
    private AttachmentRepository attachmentRepo;

    @Autowired
    public DebtDashboardService(BookmarkRepository bookmarkRepo, AnnouncementRepository announcementRepo, SecurityRepository securityRepo, IsinRepository isinRepo, DebtSecurityMasterRepository debtSecurity, CommunicationRepository communicationRepo, FinYearsRepository finYearsRepo, ExceptionsRepository exceptionRepo, WatchlistRepository watchlistRepo, AttachmentRepository attachmentRepo) {
        this.bookmarkRepo = bookmarkRepo;
        this.announcementRepo = announcementRepo;
        this.securityRepo = securityRepo;
        this.isinRepo = isinRepo;
        this.debtSecurity = debtSecurity;
        this.communicationRepo = communicationRepo;
        this.finYearsRepo = finYearsRepo;
        this.exceptionRepo = exceptionRepo;
        this.watchlistRepo = watchlistRepo;
        this.attachmentRepo = attachmentRepo;
    }

    @Override
    public ResponseEntity<Status> saveBookmark(BookmarkRequest bookmark){
        for(int i = 0; i < bookmark.getAnnouncementUuid().size(); i++) {
            Announcements ann = this.announcementRepo.findByAnnId(bookmark.getAnnouncementUuid().get(i));
            Bookmarks bookmarkEntity = Bookmarks.builder()
                    .createdBy(Constants.SYS_ADMIN)
                    .createdDt(LocalDateTime.now())
                    .updatedBy(Constants.SYS_ADMIN)
                    .updatedDt(LocalDateTime.now())
                    .entityId(Constants.SYS_ADMIN)
                    .isDelete(false)
                    .announcement(ann)
                    .build();
            this.bookmarkRepo.save(bookmarkEntity);
        }
        return ResponseEntity.ok()
                .body(Status.builder().status("SAVED").build());
    }

    @Override
    public ResponseEntity<Status> deleteBookmark(BookmarkRequest bookmark){
        try{
            for(int i = 0; i < bookmark.getAnnouncementUuid().size(); i++) {
                Long bookmarkId = this.bookmarkRepo.getBookmarkByAnnId(bookmark.getAnnouncementUuid().get(i));
                this.bookmarkRepo.deleteByAnnId(bookmarkId);
            }
            return ResponseEntity.ok()
                    .body(Status.builder().status("DELETED BOOKMARK").build());
        } catch (Exception exe) {
            log.info("__________________________ERROR IN DELETING BOOKMARK");
            return ResponseEntity.badRequest()
                    .body(Status.builder().build());
        }
    }

    @Override
    public ResponseEntity<Status> updateAnnouncementStatus(String announcementUuid, Status status){
        try{
            this.announcementRepo.updateAnnouncementStatus(announcementUuid, status.getStatus());
            return ResponseEntity.ok()
                    .body(Status.builder().status("ANNOUNCEMENT STATUS UPDATED").build());
        }catch (Exception exe) {
            log.info("_____________ERROR_UPDATING_ANNOUNCEMENT_STATUS");
            return ResponseEntity.badRequest()
                    .body(Status.builder().build());
        }
    }

    @Override
    public ResponseEntity<Kpi> getKpiDetails(String entityId){
        Long drafted = this.announcementRepo.getAllAnnStatus(Constants.DRAFTED);
        Long markedVerification = this.announcementRepo.getAllAnnStatus(Constants.MARKED_FOR_VERIFICATION);
        Long verified = this.announcementRepo.getAllAnnStatus(Constants.VERIFIED);
        Long changeSuggested = this.announcementRepo.getAllAnnStatus(Constants.CHANGE_SUGGESTED);
        Long critical = this.announcementRepo.getAllAnnStatus(Constants.CRITICAL);
        Long responseReceived = this.announcementRepo.getAllAnnStatus(Constants.RESPONSE_RECEIVED);
        Long disseminated = this.announcementRepo.getAllAnnStatus(Constants.DISSEMINATED);

        Kpi response = Kpi.builder()
                .changeSuggested( changeSuggested)
                .drafted(drafted)
                .critical(critical)
                .disseminated( disseminated)
                .markedVerification( markedVerification)
                .verified( verified)
                .responseReceived( responseReceived)
                .build();

        return ResponseEntity.ok()
                .body(response);
    }

    @Override
    public ResponseEntity<List<Adequacy>> getAllAdequacy() {
        List<Adequacy> allAdequacy= new ArrayList<>();
        List<String> announcementIds = this.announcementRepo.findAllAnnouncement(Constants.CONDITIONS);
        for(String annId: announcementIds){
            Announcements announcement = this.announcementRepo.findByAnnId(annId);
            Adequacy adequacy = this.getAllAdequacyData(announcement);
            allAdequacy.add(adequacy);
        }
        return ResponseEntity.ok()
                .body(allAdequacy);
    }

    private Adequacy getAllAdequacyData(Announcements announcement){
        Adequacy report = Adequacy.builder().build();
        List<Communications> allComm = this.communicationRepo.findCommunicationsByAnnouncementId(announcement.getAnnId());
        Exceptions exception = this.exceptionRepo.findByAnnId(announcement.getAnnId());

        StringBuilder communicationTypes = null;
        if(!allComm.isEmpty()){
            communicationTypes = new StringBuilder(allComm.get(0).getEmailType());
            for(Communications comm : allComm){
                communicationTypes.append(",").append(comm.getEmailType());
            }
        }
        String adequacyDate = this.getDateFromDateTime(announcement.getUpdatedDt());
        String adequacyTime = this.getTimeFromDateTime(announcement.getUpdatedDt());
        String lastUpdateDate = this.getDateFromDateTime(announcement.getUpdatedDt());

        report.setApplicationNumber(announcement.getAnnId());
        report.setOrgId(announcement.getListingCompanyId());
        report.setCompanyName(announcement.getCompanyName());
        report.setCategory(announcement.getSubjectTypeId().getCategory().getCategoryName());

        report.setSubjectType(announcement.getSubjectTypeId().getSubjectName());

        report.setAnnouncementText(announcement.getAnnouncementText());
        report.setRemarksForExchange(announcement.getRemarks());

        report.setAdequacyType(announcement.getAdequacyType());
        report.setStatus(announcement.getAnnouncementStatus());
        report.setAdequacyCreationDate(adequacyDate);
        report.setAdequacyCreationTime(adequacyTime);
        report.setAnnouncementCreatedBy(announcement.getCreatedBy());
        report.setPdfText(announcement.getOcrMaster().getPdfText());
        report.setLockedBy(announcement.getLockUpdatedBy());

        report.setLastUpdateDate(lastUpdateDate);
        report.setAnnAttachment(
                announcement.getAttachment() == null ? null :announcement.getAttachment().getFileName()
        );
        report.setExceptionReportingRemarks(
                announcement.getRemarkId() == null ? null : announcement.getRemarkId().getComment()
        );
        report.setCommunicationType(
                communicationTypes == null ? null : communicationTypes.toString()
        );
        if(exception == null){
            report.setExceptionReporting(null);
            report.setExceptionReportingRemarks(null);
            report.setExceptionReportingAttachment(null);
        } else {
            report.setExceptionReporting(exception.isRadioInputStatus() ? "Y" : "N");
            report.setExceptionReportingRemarks(exception.getRemark());
        }

        report.setCritical(this.getCritical(
                announcement.getSubjectTypeId().getCategory().getId(),
                announcement.getSubjectTypeId().getId())
        );

        return report;
    }

    private String getCritical(Long categoryId, Long subjectId){
        if((categoryId == 2L && subjectId == 7L)
            || (categoryId == 6L && subjectId == 23L)){
            return "Y";
        } else {
            return "N";
        }
    }

    private String getDateFromDateTime(LocalDateTime date){
        if(date == null) return null;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); //M/d/u
        return date.format(dateFormatter);
    }

    private String getTimeFromDateTime(LocalDateTime date){
        if(date == null) return null;
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mm:ss a", Locale.ENGLISH);
        return date.format(timeFormatter);
    }

    @Override
    public ResponseEntity<List<AnnouncementDashboard>> getAllAnnouncement(){
        List<String> allAnnId = this.announcementRepo.findAllAnnouncement(Constants.CONDITIONS);
        List<AnnouncementDashboard> announcement = new ArrayList<>();

        for(String announcementUuid : allAnnId) {
            Announcements ann = this.announcementRepo.findByAnnId(announcementUuid);
            String subjectName = this.announcementRepo.findSubject(announcementUuid);
            String subSubjectName = this.announcementRepo.findSubSubject(announcementUuid);
            String categoryName = this.announcementRepo.findCategory(announcementUuid);
            String attachmentPdf = this.announcementRepo.findAttachmentPdf(announcementUuid);
            String acknowledgementPdf = this.announcementRepo.findAttachmentPdf(announcementUuid);

            AnnouncementDashboard annDashboard = AnnouncementDashboard.builder()
                    .applicationNumber(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .companyType(ann.getCompanyType())
                    .category(categoryName)
                    .subjectType(subjectName)
                    .subSubjectType(subSubjectName)
                    .announcementText(ann.getAnnouncementText())
                    .annCreatedBy(ann.getCreatedBy())
                    .annStatus(ann.getAnnouncementStatus())
                    .adequacyStatus(ann.getAdequacyType())
                    .submissionDate(ann.getSubmissionDt())
                    .broadcastDate(ann.getBroadcastDt())
                    .attachmentPdf(attachmentPdf)
                    .acknowledgementPdf(acknowledgementPdf)
                    .build();
            announcement.add(annDashboard);
        }
        return ResponseEntity.ok()
                .body(announcement);
    }

    @Override
    public ResponseEntity<List<FinancialReport>> getFinancialReport(){
        List<CompanyMaster> financialReport = this.finYearsRepo.findAllFinancialReport();
        List<FinancialReport> allReport = new ArrayList<>();
        for(CompanyMaster finReport: financialReport){
            FinancialReport report = FinancialReport.builder()
                    .orgId(finReport.getCm_org_id() == null ? null : finReport.getCm_org_id().toString())
                    .companyName(finReport.getCm_company_name())
                    .companyStatus(finReport.getCm_status())
                    .build();

            allReport.add(report);
        }

        return ResponseEntity.ok()
                .body(allReport);
    }

    @Override
    public ResponseEntity<List<Reg50>> getReg50(){
        List<String> announcementUuid = this.announcementRepo.findAllAnnouncement(Constants.CONDITIONS);

        List<Reg50> allReg50 = new ArrayList<>();
        for(String annId : announcementUuid) {
            Announcements ann = this.announcementRepo.findByAnnId(annId);
            List<IsinEntity> isinList = this.isinRepo.findAllIsinByAnnouncement(annId);
            for(IsinEntity isin: isinList) {
                Reg50 report = this.getReg50Data(isin, ann);
                if(report.getIsin() != null){
                    allReg50.add(report);
                }
            }
        }
        return ResponseEntity.ok()
        .body(allReg50);
    }

    private Reg50 getReg50Data(IsinEntity isin, Announcements ann){
        String isinNo = isin.getIsin();
        Reg50 report = Reg50.builder().build();
        report.setIsin(isinNo);
        report.setCompanyName(ann.getCompanyName());
        Long isPrivate = this.debtSecurity.findIsinCount(isinNo);
        Long isPublic = this.securityRepo.findIsinCount(isinNo);

        String companyName = this.securityRepo.findCompanyName(isinNo);
        String securityType = "MT"; //this.securityRepo.findSecurityType(isinNo, companyName);

        String debtSecurityTradeStatus = "";
        String securityTradeStatus = "OPEN" ; //this.securityRepo.findTradeStatus(isinNo);

        if(isPrivate == 0L){
            report.setTradeStatus(debtSecurityTradeStatus);
        } else {
            report.setTradeStatus(securityTradeStatus);
        }

        if(isPrivate == 0L && isPublic == 0L){
            return report;
        }
        if(isPrivate > 0L){
            report.setPublicOrPrivate(Constants.PRIVATE);
        } else {
            report.setPublicOrPrivate(Constants.PUBLIC);
        }
        report.setUserDueDate(isin.getDueDate());
        report.setApplicationNumber(ann.getAnnId());
        report.setNatureOfPayment(isin.getNatureOfPayment());
        report.setCompanyName(companyName);
        report.setSecurityType(securityType);
        return report;
    }


    @Override
    public ResponseEntity<List<Reg57>> getReg57() {
        List<String> announcementUuid = this.announcementRepo.findAllAnnouncement(Constants.CONDITIONS);

        List<Reg57> allReg57 = new ArrayList<>();
        for(String annId : announcementUuid) {
            Announcements ann = this.announcementRepo.findByAnnId(annId);
            List<IsinEntity> isinList = this.isinRepo.findAllIsinByAnnouncement(annId);
            for(IsinEntity isin: isinList) {
                Reg57 report = this.getReg57Data(isin, ann);
                if(report.getIsin() != null){
                    allReg57.add(report);
                }
            }
        }
        return ResponseEntity.ok()
                .body(allReg57);
    }

    private Reg57 getReg57Data(IsinEntity isin, Announcements ann){
        String isinNo = isin.getIsin();
        Reg57 report = Reg57.builder().build();
        report.setIsin(isinNo);
        report.setCompanyName(ann.getCompanyName());
        Long isPrivate = this.debtSecurity.findIsinCount(isinNo);
        Long isPublic = this.securityRepo.findIsinCount(isinNo);
        if(isPrivate == 0L && isPublic == 0L){
            return report;
        }
        if(isPrivate > 0L){
            report.setPublicOrPrivate(Constants.PRIVATE);
        } else {
            report.setPublicOrPrivate(Constants.PUBLIC);
        }
        report.setUserDueDate(isin.getDueDate());
        report.setApplicationNumber(ann.getAnnId());
        report.setNatureOfPayment(isin.getNatureOfPayment());
        return report;
    }

    @Override
    public ResponseEntity<List<Reg60>> getReg60(){
        List<String> announcementUuid = this.announcementRepo.findAllAnnouncement(Constants.CONDITIONS);

        List<Reg60> allReg60 = new ArrayList<>();
        for(String annId : announcementUuid) {
            Announcements ann = this.announcementRepo.findByAnnId(annId);
            List<IsinEntity> isinList = this.isinRepo.findAllIsinByAnnouncement(annId);
            for(IsinEntity isin: isinList) {
                Reg60 report = this.getReg60Data(isin, ann);
                if(report.getIsin() != null){
                    allReg60.add(report);
                }
            }
        }
        return ResponseEntity.ok()
                .body(allReg60);
    }

    private Reg60 getReg60Data(IsinEntity isin, Announcements ann){
        String isinNo = isin.getIsin();
        Reg60 report = Reg60.builder()
                .isin(isinNo)
                .companyName(ann.getCompanyName())
                .build();
        Long isPrivate = this.debtSecurity.findIsinCount(isinNo);
        Long isPublic = this.securityRepo.findIsinCount(isinNo);
        if(isPrivate == 0L && isPublic == 0L){
            return report;
        }
        if(isPrivate > 0L){
            report.setPublicOrPrivate(Constants.PRIVATE);
        } else {
            report.setPublicOrPrivate(Constants.PUBLIC);
        }
        report.setUserDueDate(isin.getDueDate());
        report.setApplicationNumber(ann.getAnnId());
        report.setNatureOfPayment(isin.getNatureOfPayment());
        return report;
    }

    @Override
    public ResponseEntity<AdequacyFullReport> getGeneralReport(Map<String, String> header, GeneralReportRequest reportRequest){
        List<Announcements> allAnnouncements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        AdequacyFullReport report = AdequacyFullReport.builder().build();

        return null;
    }

    @Override
    public ResponseEntity<List<AnnouncementFullReport>> getAnnouncementReport(GeneralReportRequest reportRequest, Map<String, String> header) {
        List<Announcements> announcements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        List<AnnouncementFullReport> allReport = new ArrayList<>();
        for(Announcements ann : announcements) {
            Exceptions exe = this.exceptionRepo.findByAnnouncement(ann);
            AnnouncementFullReport report = AnnouncementFullReport.builder()
                    .applicationNo(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectName(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(null)
                    .announcementText(ann.getAnnouncementText())
                    .announcementSummary(ann.getAnnouncementText())
                    .pdfText(null)
                    .status(ann.getAnnouncementStatus())
                    .announcementCreatedBy(ann.getCreatedBy())
                    .adequacyType(ann.getAdequacyType())
                    .adequacyDateTime(null)
                    .handledBy(null)
                    .verifiedDateTime(null)
                    .verifiedDateTime(null)
                    .broadcastDate(this.getDateFromDateTime(ann.getBroadcastDt()))
                    .broadcastTime(this.getTimeFromDateTime(ann.getBroadcastDt()))
                    .companyRemark(ann.getRemarks())
                    .pdfMachineReadable(null)
                    .tillDisseminatedTime(this.getDifferenceTime(ann.getBroadcastDt(), ann.getSubmissionDt()))
                    .build();

            if(exe != null) {
                report.setReceivedDate(this.getDateFromDateTime(exe.getCreatedDt()));
                report.setReceivedTime(this.getDateFromDateTime(exe.getCreatedDt()));
                report.setExchangeRemark(exe.getRemark());
            }
            if(ann.getAttachment() != null) {
                DocUploadDetails doc = DocUploadDetails.builder()
                        .fileUuid(ann.getAttachment().getId())
                        .fileName(ann.getAttachment().getFileName())
                        .fileType(ann.getAttachment().getFileType())
                        .type(ann.getAttachment().getStatus())
                        .build();
                report.setAttachedFile(doc);
            }
            if(ann.getOcrMaster() != null) {
                report.setSubjectWiseKeyword(this.announcementRepo.findAllKeywords(ann.getAnnId(), "sensitive"));
                report.setGenericKeywords(this.announcementRepo.findAllKeywords(ann.getAnnId(), "generic"));
                report.setPdfText(ann.getOcrMaster().getPdfText());
            }
            allReport.add(report);
        }
        return ResponseEntity.ok().body(allReport);
    }

    @Override
    public ResponseEntity<List<ClarificationGeneralReport>> getClarificationReport(GeneralReportRequest reportRequest, Map<String, String> header){
        List<Announcements> announcements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        List<ClarificationGeneralReport> allReports = new ArrayList<>();
        for(Announcements ann : announcements){
            ClarificationGeneralReport report = ClarificationGeneralReport.builder()
                    .applicationNo(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectName(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(null)
                    .announcementText(ann.getAnnouncementText())
                    .announcementSummary(null)
                    .broadcastDate(this.getDateFromDateTime(ann.getBroadcastDt()))
                    .broadcastTime(this.getTimeFromDateTime(ann.getBroadcastDt()))
                    .status(ann.getAnnouncementStatus())
                    .build();

            List<Communications> comm = this.communicationRepo.findAllByAnnouncementAndEmailType(ann, Constants.CLARIFICATION);
            List<ClarificationEmailDetails> emails = new ArrayList<>();
            for(Communications communication : comm) {
                ClarificationEmailDetails email = ClarificationEmailDetails.builder().build();
                email.setEmailSentBy(communication.getEmailFrom());
                email.setEmailSentOn(communication.getCreatedDt());
                email.setEmailSentBy(communication.getEmailFrom());

                if(communication.getCommunicationResponse() != null){
                    email.setResponseGivenBy(communication.getCommunicationResponse().getCreatedBy());
                    email.setResponseGivenOn(communication.getCommunicationResponse().getCreatedDt());
                }
                emails.add(email);
            }
            report.setEmailChain(emails);
            allReports.add(report);
        }
        return ResponseEntity.ok().body(allReports);
    }

    @Override
    public ResponseEntity<List<WrongSubjectReport>> getWrongSubject(GeneralReportRequest reportRequest, Map<String, String> header) {
        List<Announcements> announcements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        List<WrongSubjectReport> allReports = new ArrayList<>();
        for(Announcements ann : announcements){
            WrongSubjectReport report = WrongSubjectReport.builder()
                    .applicationNo(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectName(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(null)
                    .announcementText(ann.getAnnouncementText())
                    .announcementSummary(null)
                    .broadcastDate(this.getDateFromDateTime(ann.getBroadcastDt()))
                    .broadcastTime(this.getTimeFromDateTime(ann.getBroadcastDt()))
                    .status(ann.getAnnouncementStatus())
                    .build();

            List<Communications> comm = this.communicationRepo.findAllByAnnouncementAndEmailType(ann, Constants.WRONG_SUBJECT);
            List<ClarificationEmailDetails> wrongSubjectEmails = new ArrayList<>();
            for(Communications communication : comm) {
                ClarificationEmailDetails email = ClarificationEmailDetails.builder().build();
                email.setEmailSentBy(communication.getEmailFrom());
                email.setEmailSentOn(communication.getCreatedDt());
                email.setEmailSentBy(communication.getEmailFrom());

                if(communication.getCommunicationResponse() != null){
                    email.setResponseGivenBy(communication.getCommunicationResponse().getCreatedBy());
                    email.setResponseGivenOn(communication.getCommunicationResponse().getCreatedDt());
                }
                wrongSubjectEmails.add(email);
            }
            report.setEmailChain(wrongSubjectEmails);
            allReports.add(report);
        }
        return ResponseEntity.ok().body(allReports);
    }

    @Override
    public ResponseEntity<List<InternalReport>> getInternalReport(GeneralReportRequest reportRequest, Map<String, String> header) {
        List<Announcements> announcements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        List<InternalReport> allReports = new ArrayList<>();
        for(Announcements ann : announcements){
            InternalReport report = InternalReport.builder()
                    .applicationNo(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectName(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(null)
                    .announcementText(ann.getAnnouncementText())
                    .announcementSummary(null)
                    .broadcastDate(this.getDateFromDateTime(ann.getBroadcastDt()))
                    .broadcastTime(this.getTimeFromDateTime(ann.getBroadcastDt()))
                    .status(ann.getAnnouncementStatus())
                    .build();

            List<Communications> comm = this.communicationRepo.findAllByAnnouncementAndEmailType(ann, Constants.INTERNAL);
            List<InternalReportEmail> internalEmails = new ArrayList<>();
            for(Communications communication : comm) {
                InternalReportEmail email = InternalReportEmail.builder().build();
                email.setEmailSentBy(communication.getEmailFrom());
                email.setEmailSentOn(communication.getCreatedDt());
                email.setEmailSentBy(communication.getEmailFrom());
                internalEmails.add(email);
            }
            report.setEmailChain(internalEmails);
            allReports.add(report);
        }
        return ResponseEntity.ok().body(allReports);
    }

    @Override
    public ResponseEntity<List<NonComplianceReport>> getNonComplianceReport(GeneralReportRequest reportRequest, Map<String, String> header) {
        List<Announcements> announcements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        List<NonComplianceReport> allReports = new ArrayList<>();
        for(Announcements ann : announcements){
            NonComplianceReport report = NonComplianceReport.builder()
                    .applicationNo(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectName(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(null)
                    .announcementText(ann.getAnnouncementText())
                    .announcementSummary(null)
                    .broadcastDate(this.getDateFromDateTime(ann.getBroadcastDt()))
                    .broadcastTime(this.getTimeFromDateTime(ann.getBroadcastDt()))
                    .status(ann.getAnnouncementStatus())
                    .build();

            List<Communications> comm = this.communicationRepo.findAllByAnnouncementAndEmailType(ann, Constants.NON_COMPLIANCE);
            List<InternalReportEmail> internalEmails = new ArrayList<>();
            for(Communications communication : comm) {
                InternalReportEmail email = InternalReportEmail.builder().build();
                email.setEmailSentBy(communication.getEmailFrom());
                email.setEmailSentOn(communication.getCreatedDt());
                email.setEmailSentBy(communication.getEmailFrom());
                internalEmails.add(email);
            }
            report.setEmailChain(internalEmails);
            allReports.add(report);
        }
        return ResponseEntity.ok().body(allReports);
    }

    @Override
    public ResponseEntity<List<SoftMailReport>> getSoftMailReport(GeneralReportRequest reportRequest, Map<String, String> header) {
        List<Announcements> announcements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        List<SoftMailReport> allReports = new ArrayList<>();
        for(Announcements ann : announcements){
            SoftMailReport report = SoftMailReport.builder()
                    .applicationNo(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectName(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(null)
                    .announcementText(ann.getAnnouncementText())
                    .announcementSummary(null)
                    .broadcastDate(this.getDateFromDateTime(ann.getBroadcastDt()))
                    .broadcastTime(this.getTimeFromDateTime(ann.getBroadcastDt()))
                    .status(ann.getAnnouncementStatus())
                    .build();

            List<Communications> comm = this.communicationRepo.findAllByAnnouncementAndEmailType(ann, Constants.SOFT_MAIL);
            List<InternalReportEmail> internalEmails = new ArrayList<>();
            for(Communications communication : comm) {
                InternalReportEmail email = InternalReportEmail.builder().build();
                email.setEmailSentBy(communication.getEmailFrom());
                email.setEmailSentOn(communication.getCreatedDt());
                email.setEmailSentBy(communication.getEmailFrom());
                internalEmails.add(email);
            }
            report.setEmailChain(internalEmails);
            allReports.add(report);
        }
        return ResponseEntity.ok().body(allReports);
    }

    @Override
    public ResponseEntity<List<ExceptionalReport>> getExceptionalReport(GeneralReportRequest reportRequest, Map<String, String> header) {
        List<Announcements> announcements = this.announcementRepo.findAllByAnnouncementStatusIsNotIn(Constants.CONDITIONS);
        List<ExceptionalReport> allReports = new ArrayList<>();
        for(Announcements ann : announcements) {
            ExceptionalReport report = ExceptionalReport.builder()
                    .applicationNo(ann.getAnnId())
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectName(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(null)
                    .exchangeRemark(ann.getRemarks())
                    .announcementSummary(ann.getAnnouncementText())
                    .announcementText(ann.getAnnouncementText())
                    .broadcastDate(this.getDateFromDateTime(ann.getBroadcastDt()))
                    .broadcastTime(this.getTimeFromDateTime(ann.getBroadcastDt()))
                    .build();

            if(ann.getException() != null) {
                Long exceptionId = ann.getException().getId();
                List<DocUploadDetails> allAttachment = new ArrayList<>();
                List<Attachments> allExceptionAttachment = this.attachmentRepo.findAllByRemarkExceptionId(exceptionId);
                for(Attachments att : allExceptionAttachment) {
                    DocUploadDetails doc = DocUploadDetails.builder()
                            .fileName(att.getFileName())
                            .fileType(att.getFileType())
                            .fileUuid(att.getId())
                            .type(att.getStatus())
                            .build();
                    allAttachment.add(doc);
                }
                report.setExceptionAttachments(allAttachment);
                report.setReceivedDate(this.getDateFromDateTime(ann.getException().getCreatedDt()));
                report.setReceivedTime(this.getTimeFromDateTime(ann.getException().getCreatedDt()));
                report.setExceptionalRemark(ann.getException().getRemark());
            }
            allReports.add(report);
        }
        return ResponseEntity.ok().body(allReports);
    }



    private String getDifferenceTime(LocalDateTime broadcastDt, LocalDateTime submissionDt){
        if(broadcastDt == null || submissionDt == null) return null;
        Duration duration = Duration.between(broadcastDt, submissionDt);
        Long seconds = duration.toSeconds();
        return seconds.toString();
    }


    @Override
    public ResponseEntity<List<Watchlist>> getAllWatchlist(Map<String, String> header){
        try{
            List<Watchlist> allWatchlist = this.watchlistRepo.findAllByCreatedBy(header.get(Constants.USER_ID));
            return ResponseEntity.ok().body(allWatchlist);
        } catch (Exception exe) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Override
    public ResponseEntity<Status> saveAllWatchlist(WatchListRequest watchlist, Map<String, String> header){
        List<Announcements> allAnnouncement = this.announcementRepo.findAllByListingCompanyIdIn(watchlist.getOrgId());
        long savedCount = 0L;
        for(Announcements ann : allAnnouncement){
            Watchlist newWatchList = Watchlist.builder()
                    .companyName(ann.getCompanyName())
                    .companyType(ann.getCompanyType())
                    .orgId(ann.getListingCompanyId())
                    .createdBy(header.get(Constants.USER_ID))
                    .createdDt(LocalDateTime.now())
                    .updatedBy(header.get(Constants.USER_ID))
                    .updatedDt(LocalDateTime.now())
                    .build();
            try{
                this.watchlistRepo.save(newWatchList);
                savedCount++;
            } catch (Exception exe) {
                log.error(String.format("ERROR SAVING WATCHLIST WITH ORG ID :  %s", newWatchList.getOrgId()));
            }
        }
        if(savedCount == allAnnouncement.size()){
            return ResponseEntity.ok().body(Status.builder().status("SAVED ALL").build());
        } else {
            return ResponseEntity.badRequest().body(Status.builder().status("ERROR SAVING").build());
        }
    }

    @Override
    public ResponseEntity<List<AutoEmailReport>> getAutoEmailReport(GeneralReportRequest reportRequest, Map<String, String> header){
        List<Announcements> allAutoAnnouncement = this.announcementRepo.findAllByEmailStatus(true);
        List<AutoEmailReport> allReport = new ArrayList<>();

        for(Announcements ann :allAutoAnnouncement) {
            Pageable topOne = PageRequest.of(0, 2);
            Long autoEmailCount = this.communicationRepo.countAllByAnnouncement(ann);
            List<Communications> firstEmail = this.communicationRepo.findCommunicationByAnnIdAsc(ann.getAnnId(), topOne);
            String emailsTo = firstEmail.get(0).getEmailTo() + "," + firstEmail.get(0).getEmailFrom();
            Set<String> isinNos = this.announcementRepo.findAllIsin(ann.getAnnId());
            List<SubSubject> subSubjectValues = this.announcementRepo.findAllFieldValue(ann.getAnnId());

            AutoEmailReport report = AutoEmailReport.builder()
                    .companyName(ann.getCompanyName())
                    .categoryName(ann.getSubjectTypeId().getCategory().getCategoryName())
                    .subjectType(ann.getSubjectTypeId().getSubjectName())
                    .subSubjectType(subSubjectValues)
                    .firstEmailSentOn(firstEmail.get(0).getCreatedDt())
                    .firstEmailSubject(firstEmail.get(0).getEmailSubject())
                    .secondEmailBy(firstEmail.get(1).getCreatedBy())
                    .secondEmailSentOn(firstEmail.get(1).getCreatedDt())
                    .noOfEmails(autoEmailCount)
                    .emailSentTo(this.getAllEmailTo(emailsTo))
                    .isinNo(isinNos)
                    .build();

            allReport.add(report);
        }
        return  ResponseEntity.ok().body(allReport);
    }

    private Set<String> getAllEmailTo(String emails) {
        return new HashSet<>(Arrays.asList(emails.split(",")));
    }


}
