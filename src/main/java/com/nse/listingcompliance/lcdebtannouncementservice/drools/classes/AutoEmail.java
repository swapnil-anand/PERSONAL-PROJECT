package com.nse.listingcompliance.lcdebtannouncementservice.drools.classes;

public class AutoEmail {
    private Long categoryId;
    private Long subjectTypeId;
    private Boolean autoEmailStatus;

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

    public Boolean getAutoEmailStatus() {
        return autoEmailStatus;
    }

    public void setAutoEmailStatus(Boolean autoEmailStatus) {
        this.autoEmailStatus = autoEmailStatus;
    }
}
