package com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.DataFields;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEBT_ANN_FIELDS_MASTER")
public class DataFieldMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "FIELDS_TO_DISPLAY", nullable = false)
    private String fieldsToDisplay;

    @Column(name = "FIELDS_TYPE", nullable = false)
    private String fieldsType;

    @Column(name = "FIELD_VALUE", nullable = false)
    private String fieldValue;

    @Column(name = "ISIN_ONLY_FIELD", nullable = false)
    private String isinOnlyField;

    @OneToMany(
            mappedBy = "dataField",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL
    )
    private List<SubjectFieldMapping> subjectFieldMapping;

    @OneToMany(
            mappedBy = "dataFieldMaster",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL
    )
    private List<DataFields> dataFields;

    @OneToMany(
            mappedBy = "masterField",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<SearchSubject> searchSubject;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATED_DT", nullable = false)
    private LocalDateTime createdDt;

    @Column(name = "UPDATED_BY", nullable = false)
    private String updatedBy;

    @Column(name = "UPDATED_DT", nullable = false)
    private LocalDateTime updatedDt;
}
