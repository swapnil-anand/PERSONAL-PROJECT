package com.nse.listingcompliance.lcdebtannouncementservice.constant;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {
    public static final String COMMUNICATION = "COMMUNICATION";

    public static final String ANNOUNCEMENT_ATTACHMENT = "ANNOUNCEMENT_ATTACHMENT";
    public static final String COMMUNICATION_ATTACHMENT = "COMMUNICATION_ATTACHMENT";
    public static final String COMMUNICATION_RESPONSE_ATTACHMENT = "COMMUNICATION_RESPONSE_ATTACHMENT";
    public static final String REVIEW_EXCEPTION_ATTACHMENT = "REVIEW_EXCEPTION_ATTACHMENT";
    public static final String PARAMETER_ATTACHMENT = "PARAMETER_ATTACHMENT";

    public static final String WRONG_SUBJECT = "WRONG_SUBJECT";
    public static final String CLARIFICATION = "CLARIFICATION";
    public static final String SOFT_MAIL = "SOFT_MAIL";
    public static final String NON_COMPLIANCE = "NON_COMPLIANCE";
    public static final String INTERNAL = "INTERNAL";
    public static final String EXCEPTION = "EXCEPTION";
    public static final String ANNOUNCEMENT = "ANNOUNCEMENT";

    public static final String PRIVATE = "PRIVATE";
    public static final String PUBLIC = "PUBLIC";

    public static final String USER_ID = "userid";
    public static final String ENTITY_ID = "entityid";


    public static final String ISIN = "ISIN";
    public static final String DATA_FIELDS = "DATA_FIELDS";
    public static final String DISCLAIMER = "DISCLAIMER";

    public static final String PENDING = "PENDING";
    public static final String DRAFTED = "DRAFTED";
    public static final String MARKED_FOR_VERIFICATION = "MARKED_FOR_VERIFICATION";
    public static final String VERIFIED = "VERIFIED";
    public static final String CHANGE_SUGGESTED = "CHANGE_SUGGESTED";
    public static final String CRITICAL = "CRITICAL";
    public static final String RESPONSE_RECEIVED = "RESPONSE_RECEIVED";
    public static final String DISSEMINATED = "DISSEMINATED";

    public static final String LOCKED = "LOCKED";
    public static final String UNLOCKED = "UNLOCKED";
    public static final String UPDATED = "UPDATED";
    public static final String INITIALIZE = "INITIALIZE";
    public static final String INITIALIZED = "INITIALIZED";
    public static final String CANNOT_INITIALIZED = "CANNOT_INITIALIZED";
    public static final String SYS_ADMIN = "SYS_ADMIN";
    public static final String DELETE = "DELETE";
    public static final String SAVED = "SAVED";
    public static final String ALREADY_DELETED = "ALREADY_DELETED";
    public static final String ERROR_DELETING = "ERROR_DELETING";

    public static final String NOT_SAVED = "NOT_SAVED";

    public static final String COMPANY_NAME = "entityname";
    public static final String KEYWORDS_EXCEPTION = "Keywords-Exception";

    public static final String COMPLETE = "COMPLETE";
    public static final String ADEQUACY_MANUAL = "MANUAL";

    public static final String STP_SUCCESSFUL = "STP-Successful";
    public static final String STP_EXCEPTION = "STP-Exception";

    public static final List<String> CONDITIONS = new ArrayList<>(Arrays.asList(DRAFTED, INITIALIZED));
    public static final List<String> SECURITY_TYPE = new ArrayList<>(Arrays.asList("MT", "CP", "GS", "SG","TB", "GF", "GI"));

    private Constants(){}
}
