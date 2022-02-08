package com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "TBL_DEBT_COMPANY_TYPE_SUBJECT_MAPPING")
public class CategorySubjectMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_TYPE_ID")
    private Subjects subId;


    @ManyToOne
    @JoinColumn(name = "COMPANY_TYPE_ID")
    private CompanyTypeMaster companyType;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalTime updatedDt;
}
