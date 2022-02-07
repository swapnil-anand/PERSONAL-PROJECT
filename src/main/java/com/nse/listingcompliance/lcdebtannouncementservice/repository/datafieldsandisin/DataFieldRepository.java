package com.nse.listingcompliance.lcdebtannouncementservice.repository.datafieldsandisin;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.DataFields;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementDataFields;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DataFieldRepository extends JpaRepository<DataFields, Long> {
    String DATA_FIELD_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.announcements.AnnouncementDataFields";
    @Query(
            "SELECT " + DATA_FIELD_RESPONSE_BODY  +
                    "( m.fieldsToDisplay, m.fieldsType ,d.fieldValue) " +
            "FROM DataFields d " +
            "INNER JOIN d.dataFieldMaster m " +
            "INNER JOIN d.announcement a " +
            "WHERE a.annId = :announcementUuid "
    )
    List<AnnouncementDataFields> findByAnnId(@Param(value = "announcementUuid") String announcementUuid);

}
