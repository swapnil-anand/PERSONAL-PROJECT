package com.nse.listingcompliance.lcdebtannouncementservice.entity.security;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TBL_SYMBOl")
public class Symbol {
    @Id
    @Column(name = "SYMB_ID")
    private Long symbolId;
    @Column(name = "SYMB_SYMBOL")
    private String symbolSymbol;
    @Column(name = "SYMB_COMPANY_NAME")
    private String companyName;
    @Column(name = "SYMB_CREAT_BY")
    private String symbolCreatedBy;
    @Column(name = "SYMB_CREAT_DT")
    private LocalDateTime symbolCreatedDt;
    @Column(name = "SYMB_LST_UPD_BY")
    private String symbolListUpdatedBy;
    @Column(name = "SYMB_LST_UPD_DT")
    private LocalDateTime symbolListUpdatedDt;
    @Column(name = "SYMB_BASE_SYMBOL")
    private String symbolBaseSymbol;
    @Column(name = "SYMB_CREAT_MODULE")
    private String symbolCreateModule;
    @Column(name = "SYMB_REGN_EXGH")
    private String symbolRegistrationExchange;
    @Column(name = "SYMB_IND_CODE")
    private String symbolIndiaCode;
    @Column(name = "SYMB_ISSUER_TYPE")
    private String symbolIssuerType;
    @Column(name = "SYMB_EFF_DT")
    private LocalDateTime symbolEffectiveDate;
    @Column(name = "SYMB_CAT_ID")
    private Long symbolCategoryId;
    @Column(name = "SYMB_PARENT_ID")
    private Long symbolParentId;

    @OneToMany(
            mappedBy = "symbol",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private List<Security> security;
}