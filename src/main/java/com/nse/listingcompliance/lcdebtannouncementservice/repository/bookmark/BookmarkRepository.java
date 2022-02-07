package com.nse.listingcompliance.lcdebtannouncementservice.repository.bookmark;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.bookmark.Bookmarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmarks, Long> {

    @Transactional
    @Modifying
        @Query(
                "UPDATE Bookmarks b " +
                "SET b.isDelete = true " +
                "WHERE b.id = :id"
        )
        void deleteByAnnId(@Param(value = "id") Long id);


    @Query(
            "SELECT b.id FROM Bookmarks b " +
            "INNER JOIN b.announcement a " +
            "WHERE a.annId = :announcementUuid"
    )
    Long getBookmarkByAnnId(@Param(value = "announcementUuid") String announcementUuid);
}
