package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.watchlist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatchListRequest {
    private Set<Long> orgId;
}
