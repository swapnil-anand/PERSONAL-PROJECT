package com.nse.listingcompliance.lcdebtannouncementservice.repository.datafieldsandisin;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.IsinEntity;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementIsinData;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinResponseBody;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IsinRepository extends JpaRepository<IsinEntity, Long> {
    String ISIN_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinResponseBody";
    String ISIN_ANNOUNCEMENT_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementIsinData";

    @Query(
            "SELECT " + ISIN_RESPONSE_BODY + "( " +
                " i.isin, i.dateOfBuyback , i.dueDate, i.paymentDate, i.natureOfPayment, i.outstandingIssueSize, " +
                " i.outstandingBonds, i.recordDate, i.noOfBonds " +
            ") FROM IsinEntity i " +
            "INNER JOIN i.announcement a " +
            "WHERE a.annId = :announcementUuid "
    )
    List<IsinResponseBody> getIsinByAnnouncementId(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT " + ISIN_ANNOUNCEMENT_BODY +
                    " (s.isin, s.dueDate, s.paymentDate,  s.dateOfBuyback, s.natureOfPayment, s.outstandingIssueSize, s.outstandingBonds, s.recordDate, " +
                    "s.noOfBonds) " +
            "FROM IsinEntity s " +
            "INNER JOIN s.announcement a " +
            "WHERE a.annId = :announcementUuid "
    )
    List<AnnouncementIsinData> findByAnnId(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            "SELECT i FROM IsinEntity i INNER JOIN i.announcement a WHERE a.annId = :announcementUuid "
    )
    List<IsinEntity> findAllIsinByAnnouncement(@Param(value = "announcementUuid") String announcementUuid);

}
