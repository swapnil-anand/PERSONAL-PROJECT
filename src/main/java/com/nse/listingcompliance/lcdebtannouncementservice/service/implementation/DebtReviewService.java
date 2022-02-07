package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.Attachments;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.exceptions.Exceptions;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.ParameterMapping;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.ParametersMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.SubParamterMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AttachmentRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.exceptions.ExceptionsRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.parameter.ParameterMappingRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.parameter.ParameterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.parameter.SubParameterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.exception.ExceptionRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.parameter.ParameterRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception.Attachment;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception.ExceptionResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.ParameterResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtReviewServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DebtReviewService implements DebtReviewServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(DebtReviewService.class);

    private ExceptionsRepository exceptionsRepo;
    private AnnouncementRepository announcementRepo;
    private AttachmentRepository attachmentRepo;
    private ParameterRepository parameterRepo;
    private SubParameterRepository subParameterRepo;
    private ParameterMappingRepository parameterMapRepo;

    @Autowired
    public DebtReviewService(ExceptionsRepository exceptionsRepo, AnnouncementRepository announcementRepo, AttachmentRepository attachmentRepo, ParameterRepository parameterRepo, SubParameterRepository subParameterRepo, ParameterMappingRepository parameterMapRepo) {
        this.exceptionsRepo = exceptionsRepo;
        this.announcementRepo = announcementRepo;
        this.attachmentRepo = attachmentRepo;
        this.parameterRepo = parameterRepo;
        this.subParameterRepo = subParameterRepo;
        this.parameterMapRepo = parameterMapRepo;
    }

    @Override
    public ResponseEntity<Status> saveException(String announcementUuid, ExceptionRequest exception, Map<String, String> header){
        Status status = Status.builder().build();
        Attachments attachment = null;
        Announcements ann = null;
        Exceptions exe = this.exceptionsRepo.findByAnnId(announcementUuid);
        try {
            ann = this.announcementRepo.findByAnnId(announcementUuid);
            attachment = this.attachmentRepo.findByAnnId(announcementUuid);
        } catch (Exception ex) {
            log.error(ex.toString());
        }
        if(exe != null) {
            String remark = exe.getRemark() + "\n" + exception.getComment();
            this.exceptionsRepo.updateRemark(announcementUuid, remark);
            status.setStatus(Constants.UPDATED);
            return ResponseEntity.ok()
                    .body(status);
        } else {
            Exceptions newException = Exceptions.builder()
                    .entityId(header.get(Constants.ENTITY_ID))
                    .remark(exception.getComment())
                    .announcement(ann)
                    .createdBy(header.get(Constants.USER_ID))
                    .createdDt(LocalDateTime.now())
                    .updatedBy(header.get(Constants.USER_ID))
                    .updatedDt(LocalDateTime.now())
                    .userId(header.get(Constants.USER_ID))
                    .radioInputStatus(exception.getInputStatus())
                    .build();
            try {
                this.exceptionsRepo.save(newException);
                status.setStatus(Constants.SAVED);
                return ResponseEntity.ok()
                        .body(status);
            } catch (Exception e) {
                status.setStatus(Constants.NOT_SAVED);
                return ResponseEntity.badRequest()
                        .body(status);
            }
        }
    }

    @Override
    public ResponseEntity<ParameterResponseBody> getParameterList(Long subjectId, Map<String, String> header){
        ParameterResponseBody params = ParameterResponseBody.builder()
                .parameters(this.parameterRepo.findBySubjectId(subjectId))
                .subParameters(this.subParameterRepo.findBySubjectId(subjectId))
                .build();
        return ResponseEntity.ok()
                .body(params);
    }

    @Override
    public ResponseEntity<Status> saveParameters(ParameterRequest params, Map<String, String> header){
        Announcements ann = this.announcementRepo.findByAnnId(params.getAnnouncementId());
        if(params.getAction().equals(Constants.INITIALIZE)){
            return ResponseEntity.ok()
                    .body(Status.builder().status(Constants.INITIALIZED).build());
        } else {
            for(int i = 0; i < params.getParameter().size(); i++) {
                ParametersMaster paramMaster = this.parameterRepo.findByParamId(params.getParameter().get(i).getParameterId());
                List<SubParamterMaster> subParam = this.subParameterRepo.findAllById(params.getParameter().get(i).getSubParameterId());
                for (SubParamterMaster subParamterMaster : subParam) {
                    ParameterMapping paraMap = ParameterMapping.builder()
                            .announcement(ann)
                            .parameterMaster(paramMaster)
                            .subParameter(subParamterMaster)
                            .build();
                    this.parameterMapRepo.save(paraMap);
                }
            }
            return ResponseEntity.ok()
                    .body(Status.builder().status(Constants.SAVED).build());
        }
    }

    @Override
    public ResponseEntity<ExceptionResponse> getExceptionReporting(String announcementUuid, Map<String, String> header){
        List<Attachment> exeAttachment = this.attachmentRepo.findByAnnIdException(announcementUuid);
        Exceptions exception = this.exceptionsRepo.findByAnnId(announcementUuid);
        if(exception == null) {
            return ResponseEntity.ok()
                    .body(null);
        }
        ExceptionResponse exe = ExceptionResponse.builder()
                .exceptionId(exception.getId())
                .remarks(exception.getRemark())
                .attachment(exeAttachment)
                .build();

        return ResponseEntity.ok()
                .body(exe);
    }

}
