package com.nse.listingcompliance.lcdebtannouncementservice.repository.security;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.CompanyMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.FinYears;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FinYearsRepository extends JpaRepository<FinYears, Long> {
    String FINANCIAL_RESPONSE = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.security.FinancialReportResponse";

    @Query(
            value = "SELECT c " +
                    "FROM CompanyMaster c "
    )
    List<CompanyMaster> findAllFinancialReport();
}
