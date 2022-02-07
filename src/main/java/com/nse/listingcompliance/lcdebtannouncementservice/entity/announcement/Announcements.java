package com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.DataFields;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields.IsinEntity;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.bookmark.Bookmarks;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.communication.Communications;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.AttachmentMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.Attachments;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.exceptions.Exceptions;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.Subjects;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.ParameterMapping;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.remarks.ReviewRemarks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DEBT_ANN_ANNOUNCEMENTS")
public class Announcements {
    @Id
    @Column(name = "ANN_ID", nullable = false)
    private String annId;

    @Column(name = "LISTING_COMPANY_ID")
    private Long listingCompanyId;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "SUBJECT_TYPE_ID", referencedColumnName = "id")
    private Subjects subjectTypeId;


    @OneToMany(
            mappedBy = "announcement",
            fetch = FetchType.LAZY, cascade = CascadeType.ALL
    )
    private List<DataFields> dataFields;

    @OneToMany(
            mappedBy = "announcement",
            cascade = CascadeType.ALL
    )
    private List<IsinEntity> isin;


    @OneToMany(
            mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Communications> communications;


    @OneToOne(
            mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private OcrMaster ocrMaster;

    @OneToOne(
            mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private AttachmentMaster attachmentMaster;

    @OneToOne(
            mappedBy = "announcement",
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private Attachments attachment;

    @OneToOne(
            mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private ReviewRemarks remarkId;

    @OneToOne(
            mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private Bookmarks bookmark;

    @OneToOne(
            mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private Exceptions exception;

    @OneToMany(
            mappedBy = "announcement",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private List<ParameterMapping> parameterMapping;

    @Column(name = "ANNOUNCEMENT_TEXT")
    private String announcementText;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "TYPES")
    private String type;

    @Column(name = "ADEQUACY_TYPE")
    private String adequacyType;

    @Column(name = "EMAIL_STATUS")
    private Boolean emailStatus;


    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    @Column(name = "ANNOUNCEMENT_TITLE")
    private String announcementTitle;

    @Column(name = "ANNOUNCEMENT_STATUS")
    private String announcementStatus;

    @Column(name = "LOCK_STATUS")
    private String lockStatus;

    @Column(name = "COMPANY_TYPE")
    private String companyType;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "LOCK_UPDATED_BY")
    private String lockUpdatedBy;

    @Column(name = "LOCK_UPDATED_DT")
    private LocalDateTime lockUpdatedDt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;

    @Column(name = "SUBMISSION_DT")
    private LocalDateTime submissionDt;

    @Column(name = "BROADCAST_DT")
    private LocalDateTime broadcastDt;

    @Column(name = "ADEQUACY_DT")
    private LocalDateTime adequacyDt;
    @Column(name = "HANDLED_BY")
    private String handledBy;
    @Column(name = "VERIFIED_BY")
    private String verifiedBy;
    @Column(name = "VERIFIED_DT")
    private LocalDateTime verifiedDt;
}
