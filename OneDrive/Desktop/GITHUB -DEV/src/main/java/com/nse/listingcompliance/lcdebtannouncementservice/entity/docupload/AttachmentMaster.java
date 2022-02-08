package com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload;

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
@Table(name = "DEBT_ANN_ATTACHMENTS_MASTER")
public class AttachmentMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FOLDER_INDEX")
    private String folderIndex;

    @OneToOne
    @JoinColumn(name = "ANN_ID")
    private Announcements announcement;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;
}