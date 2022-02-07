package com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata;


import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.parameter.ParametersMaster;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "DEBT_ANN_SUBJECT")
public class Subjects {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "SUBJECT_NAME")
    private String subjectName;

    @Column(name = "ANNOUNCEMENT_TEXT")
    private String announcementText;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DT")
    private LocalTime createdDt;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "UPDATED_DT")
    private LocalTime updatedDt;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Categories category;

    @OneToMany(mappedBy = "subjectTypeId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Announcements> announcement;

    @OneToMany(mappedBy = "subId", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<CategorySubjectMapping> mappings;

    @OneToMany(mappedBy = "subjects", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<SubjectFieldMapping> subjectFieldMapping;

    @OneToMany(mappedBy = "subject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ParametersMaster> parameters;

    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)
    private Set<SearchSubject> searchSubjects;

}
