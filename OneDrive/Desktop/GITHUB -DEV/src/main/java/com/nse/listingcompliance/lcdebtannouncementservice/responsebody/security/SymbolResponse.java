package com.nse.listingcompliance.lcdebtannouncementservice.responsebody.security;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SymbolResponse {
    private String symbol;
    private String companyName;
}
