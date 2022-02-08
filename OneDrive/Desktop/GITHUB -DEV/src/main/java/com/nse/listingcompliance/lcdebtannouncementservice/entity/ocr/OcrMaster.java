package com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBL_DEBT_OCR_ATTACHMENT")
public class OcrMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FILE_URL")
    private String fileUrl;

    @Column(name = "COMPANY_NAME")
    private String companyName;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PDF_WAS_SCANNED")
    private Boolean pdfWasScanned;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "PDF_TEXT")
    private String pdfText;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "ANN_ID")
    private Announcements announcement;

    @OneToMany(
            mappedBy = "ocrMasterId",
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private List<OcrKeywords> ocrKeywords;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;

}
