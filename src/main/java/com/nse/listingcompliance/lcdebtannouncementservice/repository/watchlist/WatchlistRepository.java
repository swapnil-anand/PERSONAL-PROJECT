package com.nse.listingcompliance.lcdebtannouncementservice.repository.watchlist;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.watchlist.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    List<Watchlist> findAllByCreatedBy(@Param(value = "createdBy") String createdBy);
}
