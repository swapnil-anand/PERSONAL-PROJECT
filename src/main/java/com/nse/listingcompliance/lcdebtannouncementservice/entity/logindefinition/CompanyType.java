package com.nse.listingcompliance.lcdebtannouncementservice.entity.logindefinition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBL_DEBT_COMPANY_LOGIN_DEFINITION")
public class CompanyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false )
    private Long id;

    @Column(name = "COMPANY_NAME" )
    private String companyName;

    @Column(name = "ORG_ID" )
    private Long orgId;

    @Column(name = "COMPANY_TYPE" )
    private Long companyLoginType;

    @Column(name = "CREATED_DT" )
    private LocalDateTime createdDt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DT" )
    private LocalDateTime updatedDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;
}
