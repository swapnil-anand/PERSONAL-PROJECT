package com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TBL_DEBT_OCR_KEYWORDS")
public class OcrKeywords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "KEYWORD")
    private String keyword;

    @Column(name = "PAGE_NO")
    private Long pageNo;

    @Column(name = "KEYWORD_TYPE")
    private String keywordType;

    @Column(name = "NEGATIVE_GENERIC_KEYWORDS_FOUND")
    private String negativeGenericKeywordsFound;

    @Column(name = "NEGATIVE_SUBJECT_KEYWORDS_FOUND")
    private String negativeSubjectKeywordsFound;

    @ManyToOne
    @JoinColumn(name = "OCR_MASTER_ID")
    private OcrMaster ocrMasterId;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "CREATED_DT")
    private LocalTime createdDt;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "UPDATED_DT")
    private LocalTime updatedDt;
}
