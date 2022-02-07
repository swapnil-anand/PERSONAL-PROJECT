package com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.DataFieldMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.FieldMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataFieldMasterRepository extends JpaRepository<DataFieldMaster, Long> {

    String DATA_FIELD_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.FieldMaster";

    @Query(
            "SELECT " + DATA_FIELD_RESPONSE_BODY + "( s.id , d.id, d.fieldsToDisplay , d.fieldsType , d.fieldValue, d.isinOnlyField ) FROM DataFieldMaster d " +
            "INNER JOIN d.subjectFieldMapping m " +
            "INNER JOIN m.subjects s " +
            "WHERE d.type = :type"
    )
    List<FieldMaster> findByFieldDisplay(@Param(value = "type") String fieldsType);


    @Query(
            "SELECT m FROM DataFieldMaster m WHERE m.id = :id "
    )
    DataFieldMaster findByMasterId(@Param(value = "id") Long id);

}
