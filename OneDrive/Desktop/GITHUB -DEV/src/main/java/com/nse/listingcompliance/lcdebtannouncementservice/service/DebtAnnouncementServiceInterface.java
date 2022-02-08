package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.Announcement;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.AttachmentStatus;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.remarks.RemarkCheck;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementStatus;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AttachmentList;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.initializes.CommonInitialize;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.getRemarks;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DebtAnnouncementServiceInterface {

    ResponseEntity<Status> initializeAnnouncement(String announcementUuid, Map<String, String> header) throws CommonException, JsonProcessingException;

    ResponseEntity<List<AnnouncementStatus>> updateAnnouncements(Announcement announcements, Map<String, String> header) ;

    ResponseEntity<AnnouncementResponseBody> getAnnouncementById(String announcementUuid, Map<String, String> header);

    ResponseEntity<List<AttachmentList>> getAllAttachmentListByUser(Map<String, String> header);

    ResponseEntity<DocUploadDetails> uploadAttachment(AttachmentStatus attachmentType, MultipartFile file, Map<String, String> header) throws CommonException, JsonProcessingException;

    ResponseEntity<List<getRemarks>> getReviews(String announcementUuid);

    ResponseEntity<Status> getRemarkStatus(String announcementUuid);

    ResponseEntity<Status> lockAnnouncement(String announcementUuid, Boolean status, Map<String, String> header);

    ResponseEntity<CommonInitialize> checkAndUpdateVerification(String announcementUuid, RemarkCheck remarks, Map<String, String> header);

}
