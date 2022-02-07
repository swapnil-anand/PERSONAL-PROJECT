package com.nse.listingcompliance.lcdebtannouncementservice.drools.classes;


public class Adequacy {
    private Long categoryId;
    private Long subjectTypeId;
    private String adequacyType;
    private String applicationStatus;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubjectTypeId() {
        return subjectTypeId;
    }

    public void setSubjectTypeId(Long subjectTypeId) {
        this.subjectTypeId = subjectTypeId;
    }

    public String getAdequacyType() {
        return adequacyType;
    }

    public void setAdequacyType(String adequacyType) {
        this.adequacyType = adequacyType;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
