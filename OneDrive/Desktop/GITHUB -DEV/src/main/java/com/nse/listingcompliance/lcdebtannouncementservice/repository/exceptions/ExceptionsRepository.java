package com.nse.listingcompliance.lcdebtannouncementservice.repository.exceptions;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.exceptions.Exceptions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ExceptionsRepository extends JpaRepository<Exceptions, Long> {

    @Query(
            "SELECT e FROM Exceptions e INNER JOIN e.announcement a WHERE a.annId = :announcementUuid"
    )
    Exceptions findByAnnId(@Param(value = "announcementUuid") String announcementUuid);

    @Modifying
    @Transactional
    @Query(
            "UPDATE Exceptions e " +
            "SET e.remark = :remark " +
            "WHERE e.announcement.annId = :announcementUuid "
    )
    void updateRemark(@Param(value = "announcementUuid") String announcementUuid,
                      @Param(value = "remark") String remark);

    Exceptions findByAnnouncement(@Param(value = "announcement") Announcements announcement);
}
