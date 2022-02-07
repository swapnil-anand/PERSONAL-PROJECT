package com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Announcement {
    private String action;
    private String companyType;
    private List<AnnouncementData> announcements;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnnouncementData {
        private String announcementId;
        private Long categoryId;
        private Long subjectTypeId;
        private List<DataFeilds> dataFields;
        private String announcementText;
        private String attachmentId;
        private String remarks;
        private String announcementStatus;
        private String announcementTitle;

        @Getter
        @Setter
        @NoArgsConstructor
        @AllArgsConstructor
        public static class DataFeilds {
            private Long id;
            private Long subjectId;
            private String fieldDisplay;
            private String fieldType;
            private String fieldValue;
            private String selectedValue;
            private String isinOnlyField;
        }

    }
}
