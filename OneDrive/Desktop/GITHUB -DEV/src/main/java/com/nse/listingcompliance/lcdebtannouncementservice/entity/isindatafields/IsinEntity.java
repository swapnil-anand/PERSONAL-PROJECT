package com.nse.listingcompliance.lcdebtannouncementservice.entity.isindatafields;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "DEBT_ANN_ISIN")
public class IsinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ISIN_ID")
    private Long id;

    @Column(name = "ISIN")
    private String isin;

    @Column(name = "DUE_DATE")
    private LocalDateTime dueDate;

    @Column(name = "PAYMENT_DATE")
    private LocalDateTime paymentDate;

    @Column(name = "NATURE_OF_PAYMENT")
    private String natureOfPayment;

    @Column(name = "OUTSTANDING_ISSUE_SIZE")
    private String outstandingIssueSize;

    @Column(name = "OUTSTANDING_BONDS")
    private String outstandingBonds;

    @Column(name = "RECORD_DATE")
    private LocalDateTime recordDate;

    @Column(name = "NO_OF_BONDS")
    private String noOfBonds;

    @Column(name = "DATE_OF_BUYBACK")
    private LocalDateTime dateOfBuyback;

    @ManyToOne
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