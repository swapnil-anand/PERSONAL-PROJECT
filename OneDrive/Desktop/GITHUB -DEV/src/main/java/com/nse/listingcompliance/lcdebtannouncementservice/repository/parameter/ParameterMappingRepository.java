package com.nse.listingcompliance.lcdebtannouncementservice.repository.parameter;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.ParameterMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParameterMappingRepository extends JpaRepository<ParameterMapping, Long> {
}
