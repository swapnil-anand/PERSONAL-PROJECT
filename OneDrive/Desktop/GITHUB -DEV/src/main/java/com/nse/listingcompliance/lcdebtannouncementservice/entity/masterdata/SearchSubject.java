package com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "DEBT_ANN_SEARCH_SUBJECT")
public class SearchSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    private Subjects subject;

    @ManyToOne
    @JoinColumn(name = "MASTER_FIELD_ID")
    private DataFieldMaster masterField;

    @Column(name = "VALUE")
    private String value;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;
}
