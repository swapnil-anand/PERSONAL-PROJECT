package com.nse.listingcompliance.lcdebtannouncementservice.repository.parameter;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.SubParamterMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.ParameterMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.SubParameters;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubParameterRepository extends JpaRepository<SubParamterMaster, Long> {
    String SUB_PARAMETER_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.SubParameters";
    @Query(
            "SELECT " + SUB_PARAMETER_RESPONSE_BODY  + " (s.id, s.subParameterName, p.id) FROM SubParamterMaster s " +
            "INNER JOIN s.parameterMaster p " +
            "INNER JOIN p.subject u " +
            "WHERE u.id = :subjectId"
    )
    List<SubParameters> findBySubjectId(@Param(value = "subjectId") Long subjectId);


    @Query(
            value = "SELECT s.subParameterName FROM Announcements a " +
                    "INNER JOIN a.parameterMapping m " +
                    "INNER JOIN m.parameterMaster p " +
                    "INNER JOIN m.subParameter s " +
                    "WHERE a.annId = :annId AND p.id = :id"
    )
    List<String> findAllByParameterMasterIn(@Param(value = "annId") String annId,
                                                       @Param(value = "id") Long id);
}
