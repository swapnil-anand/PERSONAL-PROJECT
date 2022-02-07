package com.nse.listingcompliance.lcdebtannouncementservice.constant;

public final class ApplicationConstants {

    private ApplicationConstants(){}

    public static final String ENTITY_SOURCE_ID = "entitysourceid";
    public static final String ENTITY_TYPE_ID = "entitytypeid";

    //
    public static final String LISTING_FOLDER= "/Member Compliance/Stpi/STPI Listing file ";

    //email
    public static final String SENDER_ID = "no-reply@nsefuat.nseindia.com";
    public static final String HTML_BODY = "<p>Dear Sir/Madam, <br> Please find shared folder location at  </p>";
    public static final String REPLY_TO = "all.replies@somedomain.com";
    public static final String SUBJECT = "STPI Listing file folder created";
    public static final String TEXT = "Test Mail Body";
    public static final String RECEIVER_ID = "wipro_vchavan@vendor.nse.co.in";
    public static final String FUNC_OR_SERVICEID = "11111";
    public static final String FUNC_OR_SERVICENAME = "AAAAAA";
    public static final String INVOCATION_TYPE = "SYNC";
    public static final String WEBHOOKURL_FLAG = "N";
    public static final String WEBHOOKURL = "";
    public static final String BULK_ID = "";

    //1st businessDay email
    public static final String REDIRECT_URL = "";
    public static final String START_SUBMISSION_SUBJECT = "Data required for computation of Securities Transaction Price Index (STPI) for ";

    //Test email
    public static final String TEST_EMAIL = "This is Test email Please Ignore";

    //7thday warning msil
    public static final String MID_SUBMISSION_WARN_SUBJECT = "Reminder to submit the data required for computation of Securities Transaction Price Index (STPI) for ";
    public static final String LAST_SUBMISSION_WARN_SUBJECT = "Final Reminder to submit the data required for computation of Securities Transaction Price Index (STPI) for ";

    //All submission done mail subject
    public static final String ALL_SUBMISSION_DONE_SUBJECT = "STPI member submissions complete for quarter ";
    public static final String NSE_HO_MAIL_ID = " ";

    //ReportSent to sebi
    public static final String REPORT_SENT_TO_SEBI = "";
}
