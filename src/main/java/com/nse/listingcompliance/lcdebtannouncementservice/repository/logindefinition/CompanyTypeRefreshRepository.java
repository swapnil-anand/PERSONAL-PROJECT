package com.nse.listingcompliance.lcdebtannouncementservice.repository.logindefinition;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.logindefinition.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CompanyTypeRefreshRepository extends JpaRepository<CompanyType, Long> {
    @Transactional
    @Modifying
    @Query(
            value = "UPDATE CompanyType c " +
                    "SET c.companyLoginType = :type " +
                    "WHERE c.orgId = :orgId "
    )
    void updateCompanyType(@Param(value = "orgId") Long orgId, @Param(value = "type") Long type);
}
