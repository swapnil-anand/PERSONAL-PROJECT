package com.nse.listingcompliance.lcdebtannouncementservice.entity.communication;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "TBL_DEBT_EMAIL_COMMUNICATION")
@Builder
public class Communications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "EMAIL_FROM")
    private String emailFrom;

    @Column(name = "EMAIL_TO")
    private String emailTo;

    @Column(name = "EMAIL_CC")
    private String emailCc;

    @Column(name = "EMAIL_SUBJECT")
    private String emailSubject;

    @Column(name = "EMAIL_BODY")
    private String emailBody;

    @Column(name = "EMAIL_TYPE")
    private String emailType;

    @Column(name = "STATUS")
    private String status;

    @OneToOne(
            mappedBy = "communication",
            fetch =  FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    private CommunicationResponse communicationResponse;

    @Column(name = "AUTO_EMAIL")
    private Boolean autoEmail;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;


    @ManyToOne
    @JoinColumn(name = "ANN_ID")
    private Announcements announcement;
}
