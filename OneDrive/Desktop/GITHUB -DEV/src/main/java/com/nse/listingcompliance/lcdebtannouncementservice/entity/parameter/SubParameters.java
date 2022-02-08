package com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "DEBT_ANN_SUB_PARAMETERS_MASTER")
public class SubParameters {
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Long id;

        @Column(name = "SUB_PARAMETER_NAME")
        private String subParameterName;

        @Column(name = "CREATED_BY")
        private String createdBy;
        @Column(name = "CREATED_DT")
        private LocalTime createdDt;
        @Column(name = "UPDATED_BY")
        private String updatedBy;
        @Column(name = "UPDATED_DT")
        private LocalTime updatedDt;

        @ManyToOne
        @JoinColumn(name = "PARAMETER_MASTER_ID")
        private Parameters parameter;
}
