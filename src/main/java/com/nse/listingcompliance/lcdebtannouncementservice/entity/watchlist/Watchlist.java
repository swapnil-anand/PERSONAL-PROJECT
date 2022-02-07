package com.nse.listingcompliance.lcdebtannouncementservice.entity.watchlist;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "DEBT_ANN_WATCHLIST")
public class Watchlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "ORG_ID",  nullable = false)
    private Long orgId;
    @Column(name = "COMPANY_NAME", nullable = false)
    private String companyName;
    @Column(name = "COMPANY_TYPE", nullable = false)
    private String companyType;
    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;
    @Column(name = "CREATED_DT", nullable = false)
    private LocalDateTime createdDt;
    @Column(name = "UPDATED_BY", nullable = false)
    private String updatedBy;
    @Column(name = "UPDATED_DT", nullable = false)
    private LocalDateTime updatedDt;
}
