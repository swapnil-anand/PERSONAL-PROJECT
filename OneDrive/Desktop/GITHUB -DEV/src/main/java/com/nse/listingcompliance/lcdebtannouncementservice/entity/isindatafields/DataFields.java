package com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.DataFieldMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
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
@Table(name = "DEBT_ANN_DATA_FIELDS")
public class DataFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ANN_ID")
    private Announcements announcement;

    @Column(name = "FIELD_VALUE")
    private String fieldValue;

    @ManyToOne
    @JoinColumn(name = "FIELDS_MASTER_ID")
    private DataFieldMaster dataFieldMaster;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;
}

