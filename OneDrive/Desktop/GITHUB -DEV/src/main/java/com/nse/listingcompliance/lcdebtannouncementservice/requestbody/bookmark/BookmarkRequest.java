package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.bookmark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookmarkRequest {
    private List<String> announcementUuid;
}
