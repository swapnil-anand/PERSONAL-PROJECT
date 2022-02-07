package com.nse.listingcompliance.lcdebtannouncementservice.repository.security;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.DebtSecurityMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.isin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface DebtSecurityMasterRepository extends JpaRepository<DebtSecurityMaster, Long> {

    String ISIN_ALL_RESPONSE = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.isin" ;
    @Query(
            value = "SELECT " + ISIN_ALL_RESPONSE +
                    " (d.dsm_isin_code, 'Private') FROM DebtSecurityMaster d WHERE d.dsm_cm_id = 700"
    )
    List<isin> findForIsin();

    @Query(
            "SELECT COUNT(d.dsm_isin_code) FROM DebtSecurityMaster d WHERE d.dsm_isin_code = :isin "
    )
    Long findIsinCount(@Param(value = "isin") String isin);

    @Query(
            value = "SELECT d.dsm_maturity_dt FROM DebtSecurityMaster d " +
                    "WHERE d.dsm_isin_code = :isin"
    )
    LocalDateTime findMatDate(@Param(value = "isin") String isin);

    @Query(
            value = "SELECT d.dsm_issuer_name FROM DebtSecurityMaster d WHERE d.dsm_cm_id = :cmId"
    )
    String findByDsm_cm_id(@Param(value = "cmId") Long cmId);

}

