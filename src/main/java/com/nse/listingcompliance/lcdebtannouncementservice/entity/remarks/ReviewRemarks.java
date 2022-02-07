package com.nse.listingcompliance.lcdebtannouncementservice.entity.remarks;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEBT_ANN_REVIEW_REMARKS")
public class ReviewRemarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    @Column(name = "ENTITY_ID")
    private String entityId;
    @Column(name = "COMMENTS")
    private String comment;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "TYPE")
    private String type;
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

}