package com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.CompanyTypeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTypeMasterRepository extends JpaRepository<CompanyTypeMaster, Long> {

    @Query(
            "SELECT c.companyType FROM CompanyTypeMaster c WHERE c.id = :id"
    )
    String findCompanyTypeById(@Param(value = "id") long id);
}
