package com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.Subjects;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subjects, Long> {
    String SUBJECT_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.Subject";
    @Query(
            "SELECT "+ SUBJECT_RESPONSE_BODY + " (s.id, s.subjectName, s.announcementText , c.id) FROM Subjects s " +
            "INNER JOIN s.category c " +
            "INNER JOIN s.mappings m " +
            "INNER JOIN m.companyType a " +
            "WHERE a.id = :companyType "
    )
    List<Subject> findByCompanyType(@Param(value = "companyType") Long companyType);

    @Query(
            "SELECT " + SUBJECT_RESPONSE_BODY + " (s.id, s.subjectName, s.announcementText, c.id ) FROM Subjects s " +
            "INNER JOIN s.category c "
    )
    List<Subject> findAllCompanyType();

    @Query(
            "SELECT c.id FROM Subjects s INNER JOIN s.category c WHERE s.id = :subId "
    )
    Long findByCategory(@Param(value = "subId") Long subjectId);

}