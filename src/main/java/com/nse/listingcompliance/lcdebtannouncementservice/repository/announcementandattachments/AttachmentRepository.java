package com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.Attachments;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AttachmentList;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachments, Long> {
    String DOC_UPLOAD_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.DocUploadDetails";
    String EXCEPTION_ATTACHMENT = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.exception.Attachment";
    String ATTACHMENT_LIST = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AttachmentList";

    @Query(
            "SELECT " + DOC_UPLOAD_RESPONSE_BODY +
            " (a.id, a.fileName, a.fileType, a.status ) " +
            "FROM Attachments a " +
            "WHERE a.docIndex = :docIndex"
    )

    DocUploadDetails findByDocIndex(@Param(value = "docIndex") String docIndex);

    Attachments findByAnnouncementAnnId(@Param(value = "announcementUuid")String announcementUuid);

    @Query(
            "SELECT a FROM Attachments a WHERE a.id = :attachmentId "
    )
    Attachments findByAttachmentId(@Param(value = "attachmentId") Long attachmentId);

    @Query(
            "SELECT a FROM Attachments a INNER JOIN a.announcement t WHERE t.annId = :announcementUuid"
    )
    Attachments findByAnnId(@Param(value = "announcementUuid") String announcementUuid);


    @Query(
            "SELECT " + EXCEPTION_ATTACHMENT +
            " (a.id, a.fileName ) " +
            "FROM Attachments a INNER JOIN a.announcement t WHERE t.annId = :announcementUuid "
    )
    List<Attachment> findByAnnIdException(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT " + ATTACHMENT_LIST +
            " (a.id, a.fileName, a.fileType, a.status) " +
            "FROM Attachments a " +
            "INNER JOIN a.announcement an " +
            "WHERE an.listingCompanyId = :userId AND a.status = :attachmentType"
    )
    List<AttachmentList> findAllAttachmentByUser(@Param(value = "userId") Long userId, @Param(value = "attachmentType") String attachmentType);

    @Query(
            value = "SELECT " + DOC_UPLOAD_RESPONSE_BODY +
                    " (a.id, a.fileName, a.docType, a.status) " +
                    "FROM Attachments a WHERE a.communicationId = :communicationId "
    )
    List<DocUploadDetails> findAllByCommunicationId(@Param(value = "communicationId") Long communicationId);

    List<Attachments> findAllByRemarkExceptionId(@Param(value = "id") Long id);
}
