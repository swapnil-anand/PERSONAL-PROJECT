package com.nse.listingcompliance.lcdebtannouncementservice.repository.security;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.CompanyMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Long> {
    @Query(
            value = "SELECT m FROM CompanyMaster m WHERE m.cm_org_id = :id"
    )
    CompanyMaster findCompanyMasterByCm_org_id(@Param(value = "id") Long id);
}
