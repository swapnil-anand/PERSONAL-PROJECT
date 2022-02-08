package com.nse.listingcompliance.lcdebtannouncementservice.model.dms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class NgodataDefinitionBDO {

    private String comment;
    private String dataDefIndex;
    private String dataDefName;
    private String enableLogFlag;
    private String groupId;
    private String loginUserRights;
    private List<NgodataDefFieldBDOData> ngodataDefFieldBDO;
    private String type;

}
