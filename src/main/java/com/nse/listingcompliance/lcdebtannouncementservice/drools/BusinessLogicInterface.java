package com.nse.listingcompliance.lcdebtannouncementservice.drools;


import com.nse.listingcompliance.lcdebtannouncementservice.drools.classes.Adequacy;

public interface BusinessLogicInterface {
    Adequacy getAdequacy(Long categoryId, Long subjectTypeId);

    Boolean getAutoEmail(Long categoryId, Long subjectTypeId);
}
