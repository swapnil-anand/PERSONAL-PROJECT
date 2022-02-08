package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class FailedDocuments implements Serializable {
        private FailedDocument failedDocument;
}
