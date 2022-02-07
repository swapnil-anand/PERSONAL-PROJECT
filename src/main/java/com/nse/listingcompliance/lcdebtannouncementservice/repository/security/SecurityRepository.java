package com.nse.listingcompliance.lcdebtannouncementservice.repository.security;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.Security;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.Symbol;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.SecurityIds;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.isin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface SecurityRepository extends JpaRepository<Security, String> {
    String ISIN_ALL_RESPONSE = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.isin" ;
    String SECURITY_ID = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.SecurityIds";
    @Query(
            value = "SELECT s FROM Security s WHERE s.sec_isin_cd = :isin AND s.sec_series IN (:security)"
    )
    Security findByIsinId(@Param(value = "isin") String isin, @Param(value = "security") List<String> security);

    @Query(
            value = "SELECT " + ISIN_ALL_RESPONSE +
                    " ( s.sec_isin_cd, 'Public') FROM Security s WHERE s.sec_lname = 'GOVERNMENT OF INDIA'"
    )
    List<isin> findForIsin();
    @Query(
            "SELECT COUNT(s.sec_isin_cd) FROM Security s WHERE s.sec_isin_cd = :isin"
    )
    Long findIsinCount(@Param(value = "isin") String isin);

    @Query(
            "SELECT s.sec_mat_dt FROM Security s WHERE s.sec_isin_cd = :isin"
    )
    LocalDateTime findMatDate(@Param(value = "isin") String isin);

    @Query(
            "SELECT DISTINCT s.sec_lname FROM Security s WHERE s.sec_isin_cd = :isin"
    )
    String findCompanyName(@Param(value = "isin") String isin);

    @Query(
            "SELECT DISTINCT s.sec_series FROM Security s WHERE s.sec_isin_cd = :isin AND s.sec_lname = :companyName"
    )
    String findSecurityType(@Param(value = "isin") String isin, @Param(value = "companyName") String companyName);

    @Query(
            value = "SELECT " +
                    "CASE WHEN s.sec_susp_flg = 'N' THEN 'Open' " +
                    "ELSE 'Suspended' END " +
                    "FROM Security s WHERE s.sec_isin_cd = :isin"
    )
    String findTradeStatus(@Param(value = "isin") String isin);

    @Query(
            value = "SELECT " + SECURITY_ID +
                    "(s.sec_symbol, s.sec_series, s.sec_status) FROM Security s " +
                    "INNER JOIN s.symbol sb WHERE sb.symbolId = :id "
    )
    List<SecurityIds> findAllIds(@Param(value = "id") Long id);
}
