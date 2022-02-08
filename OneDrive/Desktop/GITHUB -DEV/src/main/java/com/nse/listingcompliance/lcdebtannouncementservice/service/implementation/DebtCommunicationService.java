package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.communication.CommunicationResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.communication.Communications;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AttachmentRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.communications.CommunicationRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.communications.CommunicationResponseRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email.Email;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.email.EmailResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.communication.InitializeCommRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.communication.Communication;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.initializes.CommonInitialize;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtCommunicationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DebtCommunicationService implements DebtCommunicationServiceInterface {
    private CommunicationRepository communicationRepo;
    private AnnouncementRepository announcementRepo;
    private CommunicationResponseRepository communicationResponseRepo;
    private AttachmentRepository attachmentRepo;

    @Autowired
    public DebtCommunicationService(CommunicationRepository communicationRepo, AnnouncementRepository announcementRepo, CommunicationResponseRepository communicationResponseRepo, AttachmentRepository attachmentRepo) {
        this.communicationRepo = communicationRepo;
        this.announcementRepo = announcementRepo;
        this.communicationResponseRepo = communicationResponseRepo;
        this.attachmentRepo = attachmentRepo;
    }

    @Override
    public ResponseEntity<List<Communication>> getCommunicationHistory(String announcementUuid, Map<String, String> header) {
        Announcements ann = this.announcementRepo.findByAnnId(announcementUuid);
        List<Communications> allComm = this.communicationRepo.findAllByAnnouncement(ann);
        List<Communication> allComResponseBody = new ArrayList<>();
        for(Communications communication : allComm){
            List<DocUploadDetails> allDocDetails = this.attachmentRepo.findAllByCommunicationId(communication.getId());
            Communication com = Communication.builder()
                    .id(communication.getId())
                    .emailSubject(communication.getEmailSubject())
                    .emailBody(communication.getEmailBody())
                    .emailType(communication.getEmailType())
                    .annId(announcementUuid)
                    .status(communication.getStatus())
                    .attachmentDetails(allDocDetails)
                    .commCreatedBy(communication.getCreatedBy())
                    .commCreatedDt(communication.getCreatedDt())
                    .build();

            if(communication.getCommunicationResponse() != null) {
                com.setResponse(communication.getCommunicationResponse().getEmailResponse());
                com.setResponseBy(communication.getCommunicationResponse().getCreatedBy());
                com.setResponseDt(communication.getCommunicationResponse().getCreatedDt());
            }
            allComResponseBody.add(com);
        }
        if(allComResponseBody.isEmpty()){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok().body(allComResponseBody);
        }
    }

    @Override
    public ResponseEntity<Status> saveCommunicationResponse(EmailResponse response, Map<String, String> header){
        Communications comm = this.communicationRepo.findSingleCommunicationsByAnnouncementId(response.getCommunicationId());
        CommunicationResponse comResponse = CommunicationResponse.builder()
                .emailResponse(response.getResponse())
                .emailResponseStatus(response.getResponseStatus())
                .createdDt(LocalDateTime.now())
                .createdBy(header.get(Constants.USER_ID))
                .updatedBy(header.get(Constants.USER_ID))
                .updatedDt(LocalDateTime.now())
                .communication(comm)
                .build();
        this.communicationResponseRepo.save(comResponse);
        return null;
    }

    @Override
    public ResponseEntity<Status> saveEmail(String announcementUuid, Email email, Map<String, String> header) {
        Announcements ann = this.announcementRepo.findByAnnId(announcementUuid);
        Communications comm = this.communicationRepo.findCommunicationsById(email.getCommunicationId());
        if(ann == null || comm == null) {
            return ResponseEntity.notFound().build();
        }
        StringBuilder to = new StringBuilder(email.getTo().get(0));
        StringBuilder cc = new StringBuilder(email.getCc().get(0));
        for(int i = 1; i < email.getTo().size(); i++){
            to.append(",").append(email.getTo().get(i));
        }
        for(int i = 1; i < email.getCc().size(); i++){
            cc.append(",").append(email.getCc().get(i));
        }
        Communications emailSave = Communications.builder()
                .id(comm.getId())
                .emailFrom(comm.getEmailFrom())
                .emailCc(cc.toString())
                .emailTo(to.toString())
                .announcement(ann)
                .emailType(email.getEmailType())
                .emailBody(email.getBody())
                .emailSubject(email.getSubject())
                .status(email.getRegulation())
                .autoEmail(false)
                .createdBy(comm.getCreatedBy())
                .updatedBy(header.get(Constants.USER_ID))
                .createdDt(comm.getCreatedDt())
                .updatedDt(LocalDateTime.now())
                .build();
        try{
            this.communicationRepo.save(emailSave);
            return ResponseEntity.ok().body(Status.builder().status("Communication Id = "+ emailSave.getId()+ " SAVED").build());
        } catch (Exception exe) {
            return ResponseEntity.badRequest().body(Status.builder().status("Communication Id = "+ emailSave.getId()+ " NOT SAVED").build());
        }
    }

    @Override
    public ResponseEntity<CommonInitialize> initializeCommunication(InitializeCommRequest communication, Map<String, String> header){
        Announcements ann = this.announcementRepo.findByAnnId(communication.getAnnouncementUuid());
        if(communication.getCommunicationType().equals(Constants.COMMUNICATION)){
            Communications newCom = Communications.builder()
                    .announcement(ann)
                    .emailFrom(communication.getCommunicationFrom())
                    .createdBy(header.get(Constants.USER_ID))
                    .createdDt(LocalDateTime.now())
                    .build();
            try{
                this.communicationRepo.save(newCom);
                return ResponseEntity.ok().body(CommonInitialize.builder().id(newCom.getId()).build());
            } catch (Exception exe) {
                return ResponseEntity.badRequest().body(CommonInitialize.builder().id(newCom.getId()).build());
            }
        } else {
            Communications comm = this.communicationRepo.findSingleCommunicationsByAnnouncementId(communication.getCommunicationId());
            CommunicationResponse response = CommunicationResponse.builder()
                    .communication(comm)
                    .createdBy(header.get(Constants.USER_ID))
                    .createdDt(LocalDateTime.now())
                    .build();
            try{
                this.communicationResponseRepo.save(response);
                return ResponseEntity.ok().body(CommonInitialize.builder().id(response.getId()).build());
            } catch (Exception exe) {
                return ResponseEntity.badRequest().body(CommonInitialize.builder().build());
            }
        }

    }
}
