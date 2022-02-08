package com.nse.listingcompliance.lcdebtannouncementservice.repository.remarks;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.remarks.ReviewRemarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ReviewRemarksRepository extends JpaRepository<ReviewRemarks, Long> {

    @Transactional
    @Modifying
        @Query(
                "UPDATE ReviewRemarks r " +
                "SET r.status = :status " +
                "WHERE r.announcement.annId = :announcementUuid "

        )
        void updateRemarkStatus(@Param(value = "announcementUuid") String announcementUuid,
                                        @Param(value = "status") String status);

    ReviewRemarks findReviewRemarksById(@Param(value = "id") Long id);
}
