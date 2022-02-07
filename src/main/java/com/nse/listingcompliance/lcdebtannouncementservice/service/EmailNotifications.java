package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.ApplicationConstants;
import com.nse.listingcompliance.lcdebtannouncementservice.constant.CommonMethods;
import com.nse.listingcompliance.lcdebtannouncementservice.constant.ErrorMessages;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.model.EmailConfigDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.model.notification.EmailNotificationContent;
import com.nse.listingcompliance.lcdebtannouncementservice.model.notification.EmailNotificationResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.model.notification.RequestEmailBody;
import com.nse.listingcompliance.lcdebtannouncementservice.proxy.NotificationServiceProxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class EmailNotifications {


//    @Autowired
//    QuarterDtlRepo quarterDtlRepo;
//
//    @Autowired
//    MemberEligibleDtlRepo memberEligibleDtlRepo;
//
//    @Autowired
//    SubmissionWindowRepo submissionWindowRepo;



    @Autowired
    NotificationServiceProxy notificationServiceProxy;

//    private EmailConfigDetails emailList() {
//
//        //find latest quarter
//        DateToQuarter toQuarter = CommonMethods.convertDateToQuarter(new Date());
//        String quarterName = toQuarter.getQuarterName();
//        QuarterDetails quarterDetails = quarterDtlRepo.findByQuarterName(quarterName);
//
//        List<MemberEligibleDetails> memberEligbleDetails = memberEligibleDtlRepo.findByQuarterDtlId(quarterDetails.getId());
//        List<String> emailList = new ArrayList<>();
//        for(MemberEligibleDetails memberDetails: memberEligbleDetails) {
//            MemberMaster memerMaster = memberDetails.getMemberMaster();
//            emailList.add(memerMaster.getEmailId());
//        }
//
//        SubmissionWindow submissionWindow = submissionWindowRepo.findByCurrentDate(new Date());
//        if(submissionWindow == null) {
//            throw new NotFoundException(ErrorMessages.NOT_FOUND_EXCEPTION);
//        }
//
//        System.out.println("emailList ######################### "+emailList);
//        return EmailConfigDetails.builder()
//                .emailList(emailList)
//                .quarterName(quarterName)
//                .submissionEndDate(String.valueOf(submissionWindow.getSubmissionEndDate()))
//                .build();
//
//    }
//
//    public void startSTPISubmission() throws CommonException {
//
//        //email notification to all eligible members
//        //list of email ids
//
//        EmailConfigDetails emailConfigDetails = emailList();
////        DateToQuarter toQuarter = CommonMethods.convertDateToQuarter(new Date());
////        String quarterName = toQuarter.getQuarterName();
////        QuarterDetails quarterDetails = quarterDtlRepo.findByQuarterName(quarterName);
////
////        List<MemberEligibleDetails> memberEligbleDetails = memberEligibleDtlRepo.findByQuarterDtlId(quarterDetails.getId());
////        List<String> emailList = new ArrayList<>();
////        for(MemberEligibleDetails memberDetails: memberEligbleDetails) {
////            MemberMaster memerMaster = memberDetails.getMemberMaster();
////            emailList.add(memerMaster.getEmailId());
////        }
////
////        SubmissionWindow submissionWindow = submissionWindowRepo.findByCurrentDate(new Date());
////        if(submissionWindow == null) {
////            throw new NotFoundException(ErrorMessages.NOT_FOUND_EXCEPTION);
////        }
//
//        String html = htmlBody(ApplicationConstants.REDIRECT_URL, emailConfigDetails.getSubmissionEndDate());
//        String text = textBody(ApplicationConstants.REDIRECT_URL, emailConfigDetails.getSubmissionEndDate());
//        String subject = ApplicationConstants.START_SUBMISSION_SUBJECT+emailConfigDetails.getQuarterName();
//        EmailNotificationContent emailNotificationContent = setEmailPayLoad(ApplicationConstants.SENDER_ID, emailConfigDetails.getEmailList(), html, text, subject);
//
//        ResponseEntity<List<EmailNotificationResponse>> sendEmail = notificationServiceProxy.emailNotification(emailNotificationContent);
//
//        List<EmailNotificationResponse> emailResponse = (List<EmailNotificationResponse>) CommonMethods.serviceNotAvailableException(sendEmail);
//    }
//
//
//
//    public void midSTPISubmission() throws CommonException {
//
//        //email notification to all eligible members
//        EmailConfigDetails emailConfigDetails = emailList();
//
//        String html = midWarnHtml(ApplicationConstants.REDIRECT_URL, emailConfigDetails.getSubmissionEndDate(), emailConfigDetails.getQuarterName());
//        String text = midWarnText(ApplicationConstants.REDIRECT_URL, emailConfigDetails.getSubmissionEndDate(), emailConfigDetails.getQuarterName());
//        String subject = ApplicationConstants.MID_SUBMISSION_WARN_SUBJECT+emailConfigDetails.getQuarterName();
//        EmailNotificationContent emailNotificationContent = setEmailPayLoad(ApplicationConstants.SENDER_ID, emailConfigDetails.getEmailList(), html, text, subject);
//
//        ResponseEntity<List<EmailNotificationResponse>> sendEmail = notificationServiceProxy.emailNotification(emailNotificationContent);
//
//        List<EmailNotificationResponse> emailResponse = (List<EmailNotificationResponse>) CommonMethods.serviceNotAvailableException(sendEmail);
//    }
//
//    public void endSTPISubmission() throws CommonException {
//
//        //email notification to all eligible members
//        EmailConfigDetails emailConfigDetails = emailList();
//
//        String html = lastWarnHtml(ApplicationConstants.REDIRECT_URL, emailConfigDetails.getQuarterName());
//        String text = lastWarnText(ApplicationConstants.REDIRECT_URL, emailConfigDetails.getQuarterName());
//        String subject = ApplicationConstants.LAST_SUBMISSION_WARN_SUBJECT+emailConfigDetails.getQuarterName();
//        EmailNotificationContent emailNotificationContent = setEmailPayLoad(ApplicationConstants.SENDER_ID, emailConfigDetails.getEmailList(), html, text, subject);
//
//        ResponseEntity<List<EmailNotificationResponse>> sendEmail = notificationServiceProxy.emailNotification(emailNotificationContent);
//
//        List<EmailNotificationResponse> emailResponse = (List<EmailNotificationResponse>) CommonMethods.serviceNotAvailableException(sendEmail);
//    }
//
//    public void allSTPIReportSubmitted() throws CommonException {
//
//        //email notification to all eligible members
//        EmailConfigDetails emailConfigDetails = emailList();
//
//        //TODO SENDER RECEIVER ID
//
//        String html = allSubmissionDoneHtml(emailConfigDetails.getQuarterName());
//        String text = allSubmissionDoneText( emailConfigDetails.getQuarterName());
//        String subject = ApplicationConstants.ALL_SUBMISSION_DONE_SUBJECT+emailConfigDetails.getQuarterName();
//        EmailNotificationContent emailNotificationContent = setEmailPayLoad(ApplicationConstants.SENDER_ID, Arrays.asList(ApplicationConstants.NSE_HO_MAIL_ID), html, text, subject);
//
//        ResponseEntity<List<EmailNotificationResponse>> sendEmail = notificationServiceProxy.emailNotification(emailNotificationContent);
//
//        List<EmailNotificationResponse> emailResponse = (List<EmailNotificationResponse>) CommonMethods.serviceNotAvailableException(sendEmail);
//
//    }
//
//    public void reportSentToSEBI() throws CommonException {
//
//        //email notification to all eligible members
//        //TODO config quarter name
//        String html = allSubmissionDoneHtml("QUARTER_NAME");
//        String text = allSubmissionDoneText( "QUARTER_NAME");
//        String subject = ApplicationConstants.REPORT_SENT_TO_SEBI+"QUARTER_NAME";
//        EmailNotificationContent emailNotificationContent = setEmailPayLoad(ApplicationConstants.SENDER_ID, Arrays.asList(ApplicationConstants.NSE_HO_MAIL_ID), html, text, subject);
//
//        ResponseEntity<List<EmailNotificationResponse>> sendEmail = notificationServiceProxy.emailNotification(emailNotificationContent);
//
//        List<EmailNotificationResponse> emailResponse = (List<EmailNotificationResponse>) CommonMethods.serviceNotAvailableException(sendEmail);
//
//    }

  //  @Scheduled(cron = "*/5 * * * * ?")
    public void testEmail() throws CommonException{
        String html = htmlBody();
        String text = textBody();
        String subject = ApplicationConstants.TEST_EMAIL;
        EmailNotificationContent emailNotificationContent = setEmailPayLoad(ApplicationConstants.SENDER_ID, Arrays.asList("wipro_vchavan@vendor.nse.co.in"), html, text, subject);

        ResponseEntity<List<EmailNotificationResponse>> sendEmail = notificationServiceProxy.emailNotification(emailNotificationContent);

        List<EmailNotificationResponse> emailResponse = (List<EmailNotificationResponse>) CommonMethods.serviceNotAvailableException(sendEmail);
    }

    private EmailNotificationContent setEmailPayLoad(String from, List<String> to, String html, String text, String subject) {
        RequestEmailBody requestEmailBody = RequestEmailBody.builder()
                .bulkId(ApplicationConstants.BULK_ID)
                .cc(Arrays.asList())
                .bcc(Arrays.asList())
                .from(from)
                .intermediateReport(String.valueOf(true))
                .html(html)
                .replyTo(ApplicationConstants.REPLY_TO)
                .subject(subject)
                .templateId(0)
                .text(text)
                .to(to)
                .build();
        EmailNotificationContent emailNotificationContent = EmailNotificationContent.builder()
                .funcOrServiceID(ApplicationConstants.FUNC_OR_SERVICEID)
                .funcOrServiceName(ApplicationConstants.FUNC_OR_SERVICENAME)
                .invocationType(ApplicationConstants.INVOCATION_TYPE)
                .requestEmailBody(requestEmailBody)
                .webHookURLFlagValue(ApplicationConstants.WEBHOOKURL_FLAG)
                .webHookURL(ApplicationConstants.WEBHOOKURL)
                .build();
        System.out.println("################################        "+emailNotificationContent);
        return emailNotificationContent;
    }

    private String textBody() {

        String textBody =
                " Please Ignore this message";
        return textBody;
    }

    private String htmlBody() {
        String htmlBody = "<p>Dear Sir/Madam, <br>  <br>\n" +
                "This is a Test email, Please Ignore <br><br>\n" +
                "From Debt Announcement Journey <br><br>\n" +
                "</span>\n" +
                "</p>";
        return htmlBody;
    }

    private String midWarnHtml(String url, String date, String quarterName) {
        String htmlBody = "<p>\n" +
                "Dear Sir/Madam, <br><br>\n" +
                "\n" +
                "The due date to submit STPI data for quarter "+quarterName+" is "+date+". Kindly proceed to submit the data within the timeline.<br><br>\n" +
                "\n" +
                "<a href='https://www.w3schools.com'>parivartan module url link </a>\n" +
                "<br><br>\n" +
                "Regards,<br>\n" +
                "Member Compliance Department\n" +
                "<br> <br> <br>\n" +
                "<span style = \"color: red\" >\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "</span>\n" +
                "</p>";
        return htmlBody;
    }

    private String midWarnText(String url, String date, String quarterName) {
        String text = " Dear Sir/Madam, \n" +
                "\n" +
                "The due date to submit STPI data for quarter "+quarterName+" is "+date+". Kindly proceed to submit the data within the timeline.<br><br>\n" +
                "\n" +
                "'"+url+"'" +
                "Regards, \n" +
                "Member Compliance Department\n" +
                "\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "\n" +
                "";
        return text;
    }

    private String lastWarnHtml(String url,  String quarterName) {
        String htmlBody = "<p>\n" +
                "Dear Sir/Madam, <br><br>\n" +
                "\n" +
                "It is noted that you have not submitted the STPI data for "+quarterName+". Please note today is the last date to submit the data, kindly proceed to submit the data within the timeline..<br><br>\n" +
                "\n" +
                "<a href='"+url+"'>parivartan module url link </a>\n" +
                "<br><br>\n" +
                "Regards,<br>\n" +
                "Member Compliance Department\n" +
                "<br> <br> <br>\n" +
                "<span style = \"color: red\" >\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "</span>\n" +
                "</p>";
        return htmlBody;
    }

    private String lastWarnText(String url,  String quarterName) {
        String text = "\n" +
                "Dear Sir/Madam, \n" +
                "\n" +
                "It is noted that you have not submitted the STPI data for "+quarterName+". Please note today is the last date to submit the data, kindly proceed to submit the data within the timeline..<br><br>\n" +
                "\n" +
                ""+url+" parivartan module url link \n" +
                "\n" +
                "Regards,\n" +
                "Member Compliance Department\n" +
                "\n" +
                "\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "\n" +
                "";
        return text;
    }

    private String allSubmissionDoneHtml(String quarterName) {
        String html = "<p>\n" +
                "The STPI reports submission is completed by all the eligible members for the quarter ended "+quarterName+".\n" +
                "<br> <br>\n" +
                "Regards,<br>\n" +
                "System\n" +
                "<br> <br> <br>\n" +
                "<span style = \"color: red\" >\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "</span>\n" +
                "</p>";
        return html;
    }

    private String allSubmissionDoneText(String quarterName) {
        String text = "\n" +
                "The STPI reports submission is completed by all the eligible members for the quarter ended "+quarterName+".\n" +
                "\n" +
                "Regards,\n" +
                "System" +
                "\n" +
                "\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "\n" +
                "";
        return text;
    }

    private String reportSentToSEBIHtml(String quarterName) {
        String html = "<p>\n" +
                "STPI SEBI report is auto-generated and sent to SEBI along with Listing files for quarter "+quarterName+"\n" +
                "<br> <br>\n" +
                "Regards,<br>\n" +
                "System\n" +
                "<br> <br> <br>\n" +
                "<span style = \"color: red\" >\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "</span>\n" +
                "</p>";
        return html;
    }

    private String reportSentToSEBIText(String quarterName) {
        String html = "\n" +
                "STPI SEBI report is auto-generated and sent to SEBI along with Listing files for quarter "+quarterName+"\n" +
                "\n" +
                "Regards,\n" +
                "System\n" +
                "\n" +
                "\n" +
                "This is a system generated email, do not reply to this email id.\n" +
                "\n" +
                "";
        return html;
    }


}
