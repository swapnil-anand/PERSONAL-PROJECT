package com.nse.listingcompliance.lcdebtannouncementservice.entity.communication;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "TBL_DEBT_EMAIL_COMMUNICATION_RESPONSE")
@Builder
public class CommunicationResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
            }
    )
    @JoinColumn(name = "COMMUNICATION_ID")
    private Communications communication;
    @Column(name = "EMAIL_RESPONSE")
    private String emailResponse;
    @Column(name = "EMAIL_RESPONSE_STATUS")
    private String emailResponseStatus;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalDateTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalDateTime updatedDt;
}