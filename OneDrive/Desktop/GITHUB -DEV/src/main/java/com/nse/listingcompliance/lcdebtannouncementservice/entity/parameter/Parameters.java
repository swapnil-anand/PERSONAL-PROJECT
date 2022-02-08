package com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.Subjects;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "DEBT_ANN_PARAMETERS_MASTER")

public class Parameters {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "PARAMETER_NAME")
    private String parameterName;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalTime updatedDt;

    @ManyToOne
    @JoinColumn(name = "SUBJECT_ID")
    private Subjects subject;

    @OneToMany(mappedBy = "parameter", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<SubParameters> subParameters;
}
