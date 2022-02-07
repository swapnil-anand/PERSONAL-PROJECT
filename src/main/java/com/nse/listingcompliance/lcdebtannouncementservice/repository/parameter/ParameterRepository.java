package com.nse.listingcompliance.lcdebtannouncementservice.repository.parameter;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.ParametersMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository extends JpaRepository<ParametersMaster, Long> {
    String PARAMETER_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.parametermaster.Parameter";
    @Query(
            value = "SELECT "+ PARAMETER_RESPONSE_BODY  + " (p.id, p.parameterName) FROM ParametersMaster p " +
                    "INNER JOIN p.subject s " +
                    "WHERE s.id = :subjectId"
    )
    List<Parameter> findBySubjectId(@Param(value = "subjectId") Long subjectId);

    @Query(
            value = "SELECT p FROM ParametersMaster p WHERE p.id = :parameterId "
    )
    ParametersMaster findByParamId(@Param(value = "parameterId") Long parameterId);
}
