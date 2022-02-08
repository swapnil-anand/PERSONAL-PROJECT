package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.drools.BusinessLogicInterface;
import com.nse.listingcompliance.lcdebtannouncementservice.drools.classes.Adequacy;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.AttachmentMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.Attachments;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.DataFields;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.DataFieldMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.Subjects;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrKeywords;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.remarks.ReviewRemarks;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.model.DocDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AttachmentRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.attachment.AttachmentMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.datafieldsandisin.DataFieldRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.datafieldsandisin.IsinRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata.CompanyTypeMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata.DataFieldMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata.SubjectRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr.OcrAttachment;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.remarks.ReviewRemarksRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.Announcement;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.AttachmentStatus;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.remarks.RemarkCheck;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.*;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.initializes.CommonInitialize;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.getRemarks;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtAnnouncementServiceInterface;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtFileServiceInterface;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtOcrServiceInterface;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class DebtAnnouncementService implements DebtAnnouncementServiceInterface {
    private static Logger log = LoggerFactory.getLogger(DebtAnnouncementService.class);

    private AnnouncementRepository announcementRepo;
    private SubjectRepository subjectRepo;
    private DataFieldMasterRepository dataFieldMasterRepo;
    private DataFieldRepository dataFieldRepo;
    private IsinRepository isinRepo;
    private DebtFileServiceInterface debtFileService;
    private AttachmentMasterRepository attachmentMasterRepo;
    private AttachmentRepository attachmentRepo;
    private BusinessLogicInterface businessLogic;
    private DebtOcrServiceInterface ocrService;
    private OcrAttachment ocrRepo;
    private ReviewRemarksRepository reviewRemarkRepo;
    private CompanyTypeMasterRepository loginRepo;


    @Autowired
    public DebtAnnouncementService(AnnouncementRepository announcementRepo, SubjectRepository subjectRepo, DataFieldMasterRepository dataFieldMasterRepo, DataFieldRepository dataFieldRepo, IsinRepository isinRepo, DebtFileServiceInterface debtFileService, AttachmentMasterRepository attachmentMasterRepo, AttachmentRepository attachmentRepo, BusinessLogicInterface businessLogic, DebtOcrServiceInterface ocrService, OcrAttachment ocrRepo, ReviewRemarksRepository reviewRemarkRepo, CompanyTypeMasterRepository loginRepo) {
        this.announcementRepo = announcementRepo;
        this.subjectRepo = subjectRepo;
        this.dataFieldMasterRepo = dataFieldMasterRepo;
        this.dataFieldRepo = dataFieldRepo;
        this.isinRepo = isinRepo;
        this.debtFileService = debtFileService;
        this.attachmentMasterRepo = attachmentMasterRepo;
        this.attachmentRepo = attachmentRepo;
        this.businessLogic = businessLogic;
        this.ocrService = ocrService;
        this.ocrRepo = ocrRepo;
        this.reviewRemarkRepo = reviewRemarkRepo;
        this.loginRepo = loginRepo;
    }

    private AnnouncementStatus deleteAnnouncement(String announcementId){
        Boolean deleteStatus = this.announcementRepo.getDeleteStatus(announcementId);
        Announcements ann = this.announcementRepo.findByAnnId(announcementId);
        if(Boolean.TRUE.equals(deleteStatus)) {
            return AnnouncementStatus.builder()
                    .annId(announcementId)
                    .companyName(ann.getCompanyName())
                    .createdDt(ann.getCreatedDt())
                    .status(Constants.ALREADY_DELETED)
                    .build();
        } else {
            try{
                this.announcementRepo.softDeleteAnnouncementById(announcementId);
                return AnnouncementStatus.builder()
                        .annId(announcementId)
                        .companyName(ann.getCompanyName())
                        .createdDt(ann.getCreatedDt())
                        .status(Constants.DELETE)
                        .build();
            } catch(Exception exe) {
                return AnnouncementStatus.builder()
                        .annId(announcementId)
                        .companyName(ann.getCompanyName())
                        .createdDt(ann.getCreatedDt())
                        .status(Constants.ERROR_DELETING)
                        .build();
            }
        }
    }

    @Override
    public ResponseEntity<Status> initializeAnnouncement(String announcementUuid, Map<String, String> header){
        Announcements newAnnouncement = Announcements.builder()
                .annId(announcementUuid)
                .companyName(header.get(Constants.COMPANY_NAME))
                .createdDt(LocalDateTime.now())
                .createdBy(header.get(Constants.USER_ID))
                .listingCompanyId(Long.valueOf(header.get(Constants.ENTITY_ID)))
                .announcementStatus(Constants.INITIALIZED)
                .build();

        try {
            this.announcementRepo.save(newAnnouncement);
            this.createAnnouncementFolder(announcementUuid, newAnnouncement, header);
            return new ResponseEntity<> (Status.builder().status(Constants.INITIALIZED).build(), HttpStatus.OK);
        } catch(Exception exe) {
            return new ResponseEntity<> (Status.builder().status(Constants.CANNOT_INITIALIZED).build(), HttpStatus.BAD_GATEWAY);
        }
    }

    @Override
    public ResponseEntity<List<AnnouncementStatus>> updateAnnouncements(Announcement announcements, Map<String, String> header) {
        String action = announcements.getAction();
        String companyType = announcements.getCompanyType();
        String adequacyType ;
        String applicationStatus ;
        Adequacy adequacy;

        List<AnnouncementStatus> statusMessages = new ArrayList<>();
        for(int i = 0; i < announcements.getAnnouncements().size(); i++) {
            if(action.equals(Constants.DELETE)) {
                statusMessages.add(this.deleteAnnouncement(announcements.getAnnouncements().get(i).getAnnouncementId()));
                continue;
            }
            String annId = announcements.getAnnouncements().get(i).getAnnouncementId();
            Announcements alreadyAnnouncement = this.announcementRepo.findByAnnId(annId);
            if(alreadyAnnouncement.getAnnouncementStatus().equals(announcements.getAnnouncements().get(i).getAnnouncementStatus()) &&
                !alreadyAnnouncement.getAnnouncementStatus().equals(Constants.DRAFTED)){
                continue;
            }
            Long categoryId = announcements.getAnnouncements().get(i).getCategoryId();
            Long subjectTypeId = announcements.getAnnouncements().get(i).getSubjectTypeId();

            Subjects subject = this.subjectRepo.getOne(announcements.getAnnouncements().get(i).getSubjectTypeId());

            Boolean autoEmailStatus = this.businessLogic.getAutoEmail(categoryId, subjectTypeId);
            List<OcrKeywords> ocrKeywords = this.ocrRepo.findById(announcements.getAnnouncements().get(i).getAnnouncementId());

            if(ocrKeywords.isEmpty()) {
                adequacy = this.businessLogic.getAdequacy(categoryId, subjectTypeId);
                adequacyType = adequacy.getAdequacyType();
                applicationStatus = adequacy.getApplicationStatus();
            } else {
                adequacyType = Constants.KEYWORDS_EXCEPTION;
                applicationStatus = Constants.DRAFTED;
            }


            for(int j = 0; j < announcements.getAnnouncements().get(i).getDataFields().size(); j++){
                DataFieldMaster dataFieldMaster = this.dataFieldMasterRepo.findByMasterId(announcements.getAnnouncements().get(i).getDataFields().get(j).getId());
                DataFields newDataField = DataFields.builder()
                        .dataFieldMaster(dataFieldMaster)
                        .fieldValue(announcements.getAnnouncements().get(i).getDataFields().get(j).getSelectedValue())
                        .announcement(alreadyAnnouncement)
                        .createdBy(Constants.SYS_ADMIN)
                        .createdDt(LocalDateTime.now())
                        .build();

                this.dataFieldRepo.save(newDataField);
            }
            if(applicationStatus == null){
                applicationStatus = announcements.getAnnouncements().get(i).getAnnouncementStatus();
            }

            Announcements newAnn = Announcements.builder()
                    .annId(alreadyAnnouncement.getAnnId())
                    .listingCompanyId(alreadyAnnouncement.getListingCompanyId())
                    .subjectTypeId(subject)
                    .announcementText(announcements.getAnnouncements().get(i).getAnnouncementText())
                    .remarks(announcements.getAnnouncements().get(i).getRemarks())
                    .type(alreadyAnnouncement.getType())
                    .adequacyType(adequacyType)
                    .adequacyDt(LocalDateTime.now())
                    .emailStatus(autoEmailStatus)
                    .isDeleted(false)
                    .announcementStatus( applicationStatus) //announcements.getAnnouncements().get(i).getAnnouncementStatus() )
                    .announcementTitle(announcements.getAnnouncements().get(i).getAnnouncementTitle())
                    .lockStatus(alreadyAnnouncement.getLockStatus())
                    .lockUpdatedBy(alreadyAnnouncement.getLockUpdatedBy())
                    .lockUpdatedDt(alreadyAnnouncement.getLockUpdatedDt())
                    .createdBy(alreadyAnnouncement.getCreatedBy())
                    .createdDt(alreadyAnnouncement.getCreatedDt())
                    .submissionDt(LocalDateTime.now())
                    .updatedBy(header.get(Constants.USER_ID))
                    .updatedDt(LocalDateTime.now())
                    .companyName(header.get(Constants.COMPANY_NAME))
                    .companyType(this.getCompanyType(companyType))
                    .broadcastDt(LocalDateTime.now())
                    .build();

            this.announcementRepo.save(newAnn);
            statusMessages.add(
                    AnnouncementStatus.builder()
                    .annId(newAnn.getAnnId())
                    .companyName(newAnn.getCompanyName())
                    .createdDt(newAnn.getCreatedDt())
                    .status(Constants.SAVED)
                    .announcementTitle(newAnn.getAnnouncementTitle())
                    .build());
        }
        return ResponseEntity.ok().body(statusMessages);
    }


    @Override
    public ResponseEntity<AnnouncementResponseBody> getAnnouncementById(String announcementUuid, Map<String, String> header) {
        List<AnnouncementDataFields> dataFields = this.dataFieldRepo.findByAnnId(announcementUuid);
        List<AnnouncementIsinData> isin = this.isinRepo.findByAnnId(announcementUuid);

        Long subjectId = this.announcementRepo.findBySubjectTypeId(announcementUuid);
        Long categoryId = this.subjectRepo.findByCategory(subjectId);

        Announcements newAnn = this.announcementRepo.findByAnnId(announcementUuid);
        if(newAnn.getLockStatus().equals(Constants.LOCKED) && !newAnn.getLockUpdatedBy().equals(header.get(Constants.USER_ID))){
            return ResponseEntity.noContent().build();
        }
        AnnouncementResponseBody ann = AnnouncementResponseBody.builder()
                .categoryId(categoryId)
                .subjectTypeId(subjectId)
                .isinData(isin)
                .dataFields(dataFields)
                .announcementText(newAnn.getAnnouncementText())
                .attachmentId("")
                .announcementStatus(newAnn.getAnnouncementStatus())
                .remarks(newAnn.getRemarks())
                .createdBy(newAnn.getCreatedBy())
                .createdDt(newAnn.getCreatedDt())
                .updatedBy(newAnn.getUpdatedBy())
                .updatedDt(newAnn.getUpdatedDt())
                .build();

        return new ResponseEntity<>(ann, HttpStatus.OK);
    }

    public ResponseEntity<List<AttachmentList>> getAllAttachmentListByUser(Map<String, String> header){
        String userId = header.get(Constants.ENTITY_ID);
        if(userId.isEmpty()){
            return ResponseEntity.badRequest().body(null);
        }
        List<AttachmentList> attachment = this.attachmentRepo.findAllAttachmentByUser(Long.valueOf(userId), Constants.ANNOUNCEMENT_ATTACHMENT);
        if(attachment == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(attachment);
    }

    private void createAnnouncementFolder(String announcementUuid, Announcements announcement, Map<String, String> header) throws CommonException, JsonProcessingException {
        Long folderIndex = this.debtFileService.createFolder(announcementUuid);
        AttachmentMaster newFolder = AttachmentMaster.builder()
                .folderIndex(folderIndex.toString())
                .announcement(announcement)
                .createdBy(header.get(Constants.USER_ID))
                .createdDt(LocalDateTime.now())
                .updatedBy(header.get(Constants.USER_ID))
                .updatedDt(LocalDateTime.now())
                .build();
        try {
            this.attachmentMasterRepo.save(newFolder);
        } catch (Exception exe){
            log.info(String.format("____ERROR: %s",exe.toString()));
        }
    }

    @Override
    public ResponseEntity<DocUploadDetails> uploadAttachment(AttachmentStatus attachmentType, MultipartFile file, Map<String, String> header) throws CommonException, JsonProcessingException {
        Attachments announcementAttachment = this.attachmentRepo.findByAnnId(attachmentType.getAnnouncementId());

        if(attachmentType.getAttachmentType().equals(Constants.ANNOUNCEMENT_ATTACHMENT) && announcementAttachment != null){
            return ResponseEntity.badRequest().body(null);
        }
        String announcementUuid = attachmentType.getAnnouncementId();
        Long folderIndex = Long.parseLong(this.announcementRepo.findForFolderIndex(announcementUuid));
        String docType = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileSize = String.valueOf(file.getSize());

        DocDetails docDetails = this.debtFileService.uploadFile(folderIndex, docType, file);

        Attachments newAttachmentDetails = Attachments.builder()
                .docType(docType)
                .listingCompanyId(Long.valueOf(header.get(Constants.ENTITY_ID)))
                .entityId(header.get(Constants.ENTITY_ID))
                .fileName(file.getOriginalFilename())
                .fileSize(fileSize)
                .fileType(docType)
                .docIndex(docDetails.getDocIndex())
                .createdBy(header.get(Constants.USER_ID))
                .createdDt(LocalDateTime.now())
                .updatedBy(header.get(Constants.USER_ID))
                .updatedDt(LocalDateTime.now())
                .build();
        Long attachmentId = attachmentType.getId();

        switch (attachmentType.getAttachmentType()) {
            case Constants.ANNOUNCEMENT_ATTACHMENT:
                newAttachmentDetails.setStatus(Constants.ANNOUNCEMENT_ATTACHMENT);
                newAttachmentDetails.setAnnouncement(this.announcementRepo.findByAnnId(announcementUuid));
                this.ocrService.addKeywords(file, attachmentType.getAnnouncementId(),header);
                break;
            case Constants.PARAMETER_ATTACHMENT:
                newAttachmentDetails.setStatus(Constants.PARAMETER_ATTACHMENT);
                newAttachmentDetails.setParameterId(attachmentId);
                break;
            case Constants.COMMUNICATION_ATTACHMENT:
                newAttachmentDetails.setStatus(Constants.COMMUNICATION_ATTACHMENT);
                newAttachmentDetails.setCommunicationId(attachmentId);
                break;
            case Constants.REVIEW_EXCEPTION_ATTACHMENT:
                newAttachmentDetails.setStatus(Constants.REVIEW_EXCEPTION_ATTACHMENT);
                newAttachmentDetails.setRemarkExceptionId(attachmentId);
                break;
            case Constants.COMMUNICATION_RESPONSE_ATTACHMENT:
                newAttachmentDetails.setStatus(Constants.COMMUNICATION_RESPONSE_ATTACHMENT);
                newAttachmentDetails.setCommunicationResponseId(attachmentId);
                break;
            default:
                return ResponseEntity.badRequest().body(DocUploadDetails.builder().build());
        }
        this.attachmentRepo.save(newAttachmentDetails);
        DocUploadDetails responseDocDetails = this.attachmentRepo.findByDocIndex(docDetails.getDocIndex());
        return new ResponseEntity<>(responseDocDetails, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<List<getRemarks>> getReviews(String announcementUuid){
        return ResponseEntity.ok()
                .body(this.announcementRepo.findReview(announcementUuid));
    }

    @Override
    public ResponseEntity<Status> getRemarkStatus(String announcementUuid){
        return ResponseEntity.ok()
                .body(this.announcementRepo.findStatus(announcementUuid));
    }

    @Override
    public ResponseEntity<Status> lockAnnouncement(String announcementUuid, Boolean status, Map<String, String> header) {
        String remarkStatus = Boolean.TRUE.equals(status) ? Constants.LOCKED : Constants.UNLOCKED;
        Status statusResponseBody = Status.builder().build();
        try {
            this.announcementRepo.AnnouncementLock(announcementUuid, remarkStatus, LocalDateTime.now(), header.get(Constants.USER_ID));
            statusResponseBody.setStatus(remarkStatus);
        } catch (Exception exe) {
            return ResponseEntity.badRequest()
                    .body(statusResponseBody);
        }
        return ResponseEntity.ok()
                .body(statusResponseBody);
    }

    @Override
    public ResponseEntity<CommonInitialize> checkAndUpdateVerification(String announcementUuid, RemarkCheck remarks, Map<String,String> header) {
        String announcementRemarks = this.announcementRepo.getRemarkByAnnId(announcementUuid) + " \n " + remarks.getRemark();
        CommonInitialize status = CommonInitialize.builder().build();
        if(Boolean.FALSE.equals(remarks.getProceedToVerification())) {
            this.announcementRepo.UpdateReviewAndStatus(announcementUuid, announcementRemarks);
        } else {
            ReviewRemarks reviewRemarks = this.addRemark(announcementUuid, remarks, header);
            this.announcementRepo.updateAnnouncementStatus(announcementUuid,Constants.MARKED_FOR_VERIFICATION);
            this.reviewRemarkRepo.save(reviewRemarks);
            status.setId(reviewRemarks.getId());
        }
        return ResponseEntity.ok()
                .body(status);
    }

    private ReviewRemarks addRemark(String announcementUuid, RemarkCheck remarks, Map<String,String> header){
        Announcements ann = this.announcementRepo.findByAnnId(announcementUuid);
        return ReviewRemarks.builder()
                .announcement(ann)
                .comment(remarks.getRemark())
                .status(Constants.DRAFTED)
                .entityId(header.get(Constants.ENTITY_ID))
                .createdBy(header.get(Constants.USER_ID))
                .createdDt(LocalDateTime.now())
                .type("")
                .build();
    }

    private String getCompanyType(String companyType) {
        long id;
        if(companyType.equals("A")){ id = 1L;}
        else if(companyType.equals("B")) {id = 2L;}
        else {id = 3L;}
        return this.loginRepo.findCompanyTypeById(id);
    }
}
