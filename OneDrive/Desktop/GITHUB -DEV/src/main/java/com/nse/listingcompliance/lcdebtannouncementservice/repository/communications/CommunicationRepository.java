package com.nse.listingcompliance.lcdebtannouncementservice.repository.communications;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.communication.CommunicationResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.communication.Communications;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunicationRepository extends JpaRepository<Communications, Long> {
    String COMMUNICATION_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.communication.Communication";

    @Query(
            value = "SELECT c FROM Communications c INNER JOIN c.announcement a WHERE a.annId = :announcementUuid "
    )
    List<Communications> findCommunicationsByAnnouncementId(@Param(value = "announcementUuid") String announcementUuid);

    @Query(
            value = "SELECT c FROM Communications c "+
                    "WHERE c.id = :communicationId "
    )
    Communications findSingleCommunicationsByAnnouncementId(@Param(value = "communicationId") Long communicationId);

    @Query(
            "SELECT r FROM Communications c "+
            "INNER JOIN c.communicationResponse r " +
            "INNER JOIN c.announcement a " +
            "WHERE a.annId = :announcementUuid AND c.id = :communicationId"
    )
    CommunicationResponse findCommunicationsResponseByAnnouncementId(@Param(value = "communicationId") Long communicationId,
                                                                           @Param(value = "announcementUuid") String announcementUuid);

    List<Communications> findAllByAnnouncement(@Param(value = "announcement") Announcements announcement);

    List<Communications> findAllByAnnouncementAndEmailType (@Param(value = "announcement")Announcements announcement,
                                                            @Param(value = "emailType") String emailType);

    Communications findCommunicationsById(@Param(value = "id") Long id);

    Long countAllByAnnouncement(@Param(value = "announcemnet") Announcements announcement);

    @Query(
            value = "SELECT c FROM Communications c " +
                    "INNER JOIN c.announcement a " +
                    "WHERE a.annId = :annId " +
                    "ORDER BY c.createdBy  ASC "
    )
    List<Communications> findCommunicationByAnnIdAsc(@Param(value = "annId") String annId, Pageable pageable);

    List<Communications> findCommunicationsByAnnouncementAndEmailType(@Param(value = "announcement") Announcements announcement,
                                                                      @Param(value = "emailType") String emailType);

}
