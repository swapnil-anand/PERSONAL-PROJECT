package com.nse.listingcompliance.lcdebtannouncementservice.repository.masterdata;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.SearchSubject;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.SearchSubjectResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchSubjectRepository extends JpaRepository<SearchSubject, Long> {
    String SEARCH_SUBJECT_RESPONSE_BODY = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.SearchSubjectResponse";


    @Query(
            "SELECT " + SEARCH_SUBJECT_RESPONSE_BODY +
            " (s.id, s.subjectName, p.id, m.id, s.value) FROM SearchSubject s " +
            "INNER JOIN s.subject p " +
            "INNER JOIN s.masterField m " +
            "WHERE p.id IN (:subjectId)"
    )
    List<SearchSubjectResponse> findAllSearchSubject(@Param(value = "subjectId") List<Long> subjectId);
}
