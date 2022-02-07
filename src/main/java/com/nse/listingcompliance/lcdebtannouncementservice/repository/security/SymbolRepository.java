package com.nse.listingcompliance.lcdebtannouncementservice.repository.security;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.Security;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.Symbol;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.security.SymbolResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymbolRepository extends JpaRepository<Symbol, Long> {
    String SYMBOL = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.security.SymbolResponse";

    @Query(
            "SELECT " + SYMBOL + " (s.symbolSymbol, s.companyName) " +
            "FROM Symbol s WHERE s.symbolCategoryId = :catId "
    )
    List<SymbolResponse> getAllCompany(@Param(value = "catId") Long catId);

    Symbol findBySymbolId(@Param(value = "symbolId") Long symbolId);
}
