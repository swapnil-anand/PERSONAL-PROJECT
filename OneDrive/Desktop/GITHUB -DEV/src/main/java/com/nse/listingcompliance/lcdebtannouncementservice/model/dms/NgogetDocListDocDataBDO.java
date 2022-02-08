package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class NgogetDocListDocDataBDO {
    private String accessedDateTime;
    private String author;
    private String checkOutBy;
    private String checkOutStatus;
    private String comment;
    private String createdByApp;
    private String createdByAppName;
    private String createdDateTime;
    private String docOrderNo;
    private String documentIndex;
    private String documentLock;
    private String documentName;
    private String documentSize;
    private String documentType;
    private String documentVersionNo;
    private String enableLog;
    private String expiryDateTime;
    private String filedByUser;
    private String filedDateTime;
    private String finalizedBy;
    private String ftsDocumentIndex;
    private String isIndex;
    private String location;
    private String lockByUser;
    private String loginUserRights;
    private NgodataDefinitionBDO ngodataDefinitionBDO;
    private String noOfPages;
    private String owner;
    private String ownerIndex;
    private String parentFolderIndex;
    private String referenceFlag;
    private String revisedDateTime;
    private String textISIndex;
    private String useFulInfo;
    private String versionFlag;
}
