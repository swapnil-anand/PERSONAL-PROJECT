package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.watchlist.Watchlist;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.generalreport.GeneralReportRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.watchlist.WatchListRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard.*;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.bookmark.BookmarkRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.*;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DebtDashboardControllerInterface {
    ResponseEntity<Status> saveBookmark(BookmarkRequest bookmark);

    ResponseEntity<Status> deleteBookmark(BookmarkRequest bookmark);

    ResponseEntity<Status> updateAnnouncementStatus(String announcementUuid, Status status);

    ResponseEntity<Kpi> getKpiDetails(String entityId);

    ResponseEntity<List<Adequacy>> getAllAdequacy();

    ResponseEntity<List<AnnouncementDashboard>> getAllAnnouncement();

    ResponseEntity<List<FinancialReport>> getFinancialReport();

    ResponseEntity<AdequacyFullReport> getGeneralReport(Map<String, String> header, GeneralReportRequest reportRequest);

    ResponseEntity<List<Reg50>> getReg50();

    ResponseEntity<List<Reg60>> getReg60();

    ResponseEntity<List<Reg57>> getReg57();

    ResponseEntity<List<Watchlist>> getAllWatchlist(Map<String, String> header);

    ResponseEntity<Status> saveAllWatchlist( WatchListRequest watchlist, Map<String, String> header);

    ResponseEntity<List<AutoEmailReport>> getAutoEmailReport(GeneralReportRequest reportRequest, Map<String, String> header);

    ResponseEntity<List<ExceptionalReport>> getExceptionalReport(GeneralReportRequest reportRequest, Map<String, String> header);

    ResponseEntity<List<SoftMailReport>> getSoftMailReport(GeneralReportRequest reportRequest, Map<String, String> header);

    ResponseEntity<List<NonComplianceReport>> getNonComplianceReport(GeneralReportRequest reportRequest, Map<String, String> header);

    ResponseEntity<List<InternalReport>> getInternalReport(GeneralReportRequest reportRequest, Map<String, String> header);

    ResponseEntity<List<WrongSubjectReport>> getWrongSubject(GeneralReportRequest reportRequest, Map<String, String> header);

    ResponseEntity<List<ClarificationGeneralReport>> getClarificationReport(GeneralReportRequest reportRequest, Map<String, String> header);

    ResponseEntity<List<AnnouncementFullReport>> getAnnouncementReport(GeneralReportRequest reportRequest, Map<String, String> header);

}
