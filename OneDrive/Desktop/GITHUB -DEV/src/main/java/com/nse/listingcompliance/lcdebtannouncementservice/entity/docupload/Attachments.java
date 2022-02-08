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
@Table(name = "DEBT_ANN_ATTACHMENTS")
public class Attachments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "LISTING_COMPANY_ID")
    private Long listingCompanyId;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "ANN_ID")
    private Announcements announcement;

    @Column(name = "PARAMETER_ID")
    private Long parameterId;

    @Column(name = "REVIEW_EXCEPTION_ID")
    private Long remarkExceptionId;

    @Column(name = "COMMUNICATION_ID")
    private Long communicationId;

    @Column(name = "COMMUNICATION_RESPONSE_ID")
    private Long communicationResponseId;

    @Column(name = "DOC_INDEX")
    private String docIndex;

    @Column(name = "DOC_TYPE")
    private String docType;

    @Column(name = "ENTITY_ID")
    private String entityId;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "FILE_SIZE")
    private String fileSize;

    @Column(name = "FILE_TYPE")
    private String fileType;

}