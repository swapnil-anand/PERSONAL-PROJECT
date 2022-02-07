package com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "DEBT_ANN_SUB_PARAMETERS_MASTER")
public class SubParamterMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "SUB_PARAMETER_NAME")
    private String subParameterName;
    @ManyToOne
    @JoinColumn(name = "PARAMETER_MASTER_ID")
    private ParametersMaster parameterMaster;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;
    @OneToMany(
            mappedBy = "subParameter",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private List<ParameterMapping> mapping;
}