package com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DEBT_ANN_PARAMETERS_MAPPING")
public class ParameterMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "ANN_ID")
    private Announcements announcement;
    @ManyToOne
    @JoinColumn(name = "PARAMETER_MASTER_ID")
    private ParametersMaster parameterMaster;
    @ManyToOne
    @JoinColumn(name = "SUB_PARAMETER_ID")
    private SubParamterMaster subParameter;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalTime updatedDt;
}
