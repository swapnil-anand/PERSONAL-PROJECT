package com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.ParametersMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.AdequacySubParameter;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.SubSubject;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.ParameterMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.getRemarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcements, String> {
    String GET_REMARKS = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.getRemarks";
    String REMARK_STATUS = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status";
    String ANNOUNCEMENT_DASHBOARD = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard.AnnouncementDashboard";
    String SUB_SUBJECT = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.SubSubject";
    String SUB_PARAMTERS = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.AdequacySubParameter";
    @Query(
            value = "SELECT a FROM Announcements a " +
                    "WHERE a.annId = :announcementUuid "
    )
    Announcements findByAnnId(@Param(value = "announcementUuid") String announcementUuid);

    @Transactional
    @Modifying
    @Query(
            "UPDATE Announcements a SET a.isDeleted = 1 WHERE a.annId = :annId"
    )
    void softDeleteAnnouncementById(@Param(value = "annId") String annId);

    @Query(
            "SELECT a.isDeleted FROM Announcements a WHERE a.annId = :annId "
    )
    Boolean getDeleteStatus(@Param(value = "annId") String annId);


    @Query(
            "SELECT s.id FROM Announcements a " +
            "INNER JOIN a.subjectTypeId s " +
            "WHERE a.annId = :announcementUuid "
    )
    Long findBySubjectTypeId(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT i.folderIndex FROM Announcements a INNER JOIN a.attachmentMaster i WHERE a.annId = :announcementUuid "
    )
    String findForFolderIndex(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT i.docIndex FROM Announcements a INNER JOIN a.attachment i WHERE a.annId = :announcementUuid "
    )
    String findForDocIndex(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT " + GET_REMARKS + " ( r.entityId, r.comment ) FROM Announcements a " +
            "INNER JOIN a.remarkId r " +
            "WHERE a.annId = :announcementUuid"
    )
    List<getRemarks> findReview(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT " + REMARK_STATUS + " (r.status) FROM Announcements a " +
            "INNER JOIN a.remarkId r " +
            "WHERE a.annId = :announcementUuid"
    )
    Status findStatus(@Param(value = "announcementUuid") String announcementUuid);

    @Transactional
    @Modifying
        @Query(
                "UPDATE Announcements a " +
                "SET a.announcementStatus = :status " +
                "WHERE a.annId = :announcementUuid "
        )
        void updateAnnouncementStatus(@Param(value = "announcementUuid") String announcementUuid, @Param(value = "status") String status);

    @Query(
            "SELECT COUNT(a.annId) FROM Announcements a " +
            "WHERE a.announcementStatus = :status AND a.announcementStatus <> '" + Constants.INITIALIZED + "'"
    )
    Long getAllAnnStatus(@Param(value = "status") String status);

    @Query(
            "SELECT a FROM Announcements a WHERE a.createdBy = :entityId "
    )
    List<Announcements> findByCreatedBy(@Param(value = "entityId") String entityId);

    @Modifying
    @Transactional
        @Query(
                "UPDATE Announcements a " +
                "SET a.lockStatus = :lockStatus, a.lockUpdatedDt = :lockTime, a.lockUpdatedBy = :lockUpdatedBy " +
                "WHERE a.annId = :announcementUuid"
        )
        void AnnouncementLock(@Param(value = "announcementUuid") String announcementUuid,
                              @Param(value = "lockStatus") String lockStatus,
                              @Param(value = "lockTime") LocalDateTime lockTime,
                              @Param(value = "lockUpdatedBy") String lockUpdatedBy);

    // --------------------------------------------- Remark and Review ---------------------------------------------------

    @Query(
        "SELECT a.remarks FROM Announcements a WHERE a.annId = :announcementUuid "
    )
    String getRemarkByAnnId(@Param(value = "announcementUuid") String announcementUuid);

    @Transactional
    @Modifying
        @Query(
                "UPDATE Announcements a " +
                "SET a.remarks = :remark " +
                "WHERE a.annId = :announcementUuid "
        )
        void UpdateReviewAndStatus(@Param(value = "announcementUuid") String announcementUuid,
                                   @Param(value = "remark") String remark);

    // ---------------------------------------------Nse Ann Dashboard-------------------------------------------------------------

    @Query(
            value = "SELECT a.annId FROM Announcements a WHERE a.announcementStatus NOT IN (:conditions) "
    )
    List<String> findAllAnnouncement(@Param(value = "conditions") List<String> conditions);

    @Query(
            "SELECT s.subjectName FROM Announcements a INNER JOiN a.subjectTypeId s WHERE a.annId = :announcementUuid"
    )
    String findSubject(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT df.fieldValue FROM Announcements a INNER JOIN a.subjectTypeId s INNER JOIN s.subjectFieldMapping sf " +
            "INNER JOIN sf.dataField d INNER JOIN d.dataFields df WHERE d.fieldsToDisplay = 'Sub-subject' AND a.annId = :announcementUuid"
    )
    String findSubSubject(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT c.categoryName FROM Announcements a INNER JOIN a.subjectTypeId s INNER JOIN s.category c " +
            "WHERE a.annId = :announcementUuid"
    )
    String findCategory(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT t.fileName FROM Announcements a INNER JOIN a.attachment t WHERE a.annId = :announcementUuid "
    )
    String findAttachmentPdf(@Param(value = "announcementUuid") String announcementUuid);

    // --------------------------------------------- Company Dashboard ----------------------------------------------------------------

    @Query(
            "SELECT COUNT(a.annId) FROM Announcements a WHERE a.listingCompanyId = :entityId AND a.announcementStatus = :status"
    )
    Long getCompanyAnnStatus(@Param(value = "entityId") Long entityId, @Param(value = "status") String status);


    List<Announcements> findAllByListingCompanyIdIn(@Param(value = "id") Set<Long> id);
    List<Announcements> findAllByAnnouncementStatusIsNotIn(@Param(value = "condition") List<String> condition);

    @Query(
            value = "SELECT k.keyword FROM Announcements a " +
                    "INNER JOIN a.ocrMaster m " +
                    "INNER JOIN m.ocrKeywords k " +
                    "WHERE a.annId = :announcementUuid AND k.keywordType = :keywordType"
    )
    List<String> findAllKeywords(@Param(value = "announcementUuid") String announcementUuid, @Param(value = "keywordType") String keywordType);

    List<Announcements> findAllByEmailStatus(@Param(value = "emailStatus") Boolean emailStatus);

    @Query(
            value = "SELECT DISTINCT i.isin FROM Announcements a " +
                    "INNER JOIN a.isin i " +
                    "WHERE a.annId = :annId "
    )
    Set<String> findAllIsin(@Param(value = "annId") String annId);

    @Query(
            value = "SELECT " + SUB_SUBJECT +
                    "(dm.fieldsToDisplay, d.fieldValue) FROM Announcements a " +
                    "INNER JOIN a.dataFields d " +
                    "INNER JOIN d.dataFieldMaster dm "+
                    "WHERE a.annId = :annId "
    )
    List<SubSubject> findAllFieldValue(@Param(value = "annId") String annId);

    @Query(
            value = "SELECT p FROM Announcements a " +
                    "INNER JOIN a.parameterMapping pm " +
                    "INNER JOIN pm.parameterMaster p " +
                    "WHERE a.annId = :annId "
    )
    List<ParametersMaster> findAllParameter(@Param(value = "annId") String annId);

}
