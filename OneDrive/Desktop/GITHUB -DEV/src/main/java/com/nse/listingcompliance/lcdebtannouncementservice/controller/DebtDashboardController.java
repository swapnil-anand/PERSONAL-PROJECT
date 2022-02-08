package com.nse.listingcompliance.lcdebtannouncementservice.controller;


import com.nse.listingcompliance.lcdebtannouncementservice.entity.watchlist.Watchlist;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.generalreport.GeneralReportRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.watchlist.WatchListRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.dashboard.*;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.bookmark.BookmarkRequest;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.generalreportsubreport.*;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtDashboardControllerInterface;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "NSE Dashboard")
@Slf4j
public class DebtDashboardController {
    private static final Logger nseDashboardLog = LoggerFactory.getLogger(DebtDashboardController.class);
    private DebtDashboardControllerInterface debtDashboardService;

    @Autowired
    public DebtDashboardController(DebtDashboardControllerInterface debtDashboardService) {
        this.debtDashboardService = debtDashboardService;
    }

    @PostMapping("/nseDashboard/bookmark")
    public ResponseEntity<Status> addBookmark(@RequestBody BookmarkRequest bookmark){
        return this.debtDashboardService.saveBookmark(bookmark);
    }

    @DeleteMapping("/nseDashboard/bookmark/delete")
    public ResponseEntity<Status> deleteBookmark(@RequestBody BookmarkRequest bookmark) {
        return this.debtDashboardService.deleteBookmark(bookmark);
    }

    @PutMapping("/nseDashboard/announcement/{announcementUuid}/status")
    public ResponseEntity<Status> updateAnnouncementStatus(@PathVariable("announcementUuid") String announcementUuid, @RequestBody Status status){
        return this.debtDashboardService.updateAnnouncementStatus(announcementUuid, status);
    }

    @GetMapping("/nseDashboard/announcements/summary")
    public ResponseEntity<Kpi> getKpiStatus(@RequestHeader Map<String, String> header) {
        header.forEach((key, value) ->
                nseDashboardLog.info(String.format("__HEADER: %s, %s", key, value))
        );
        return this.debtDashboardService.getKpiDetails(header.get("entityid"));
    }

    @GetMapping("/nseDashboard/adequacy")
    public ResponseEntity<List<Adequacy>> getAllAdequacy(){
        return this.debtDashboardService.getAllAdequacy();
    }

    @GetMapping("/nseDashboard/announcements")
    public ResponseEntity<List<AnnouncementDashboard>> getAllAnnouncement(){
        return this.debtDashboardService.getAllAnnouncement();
    }

    @GetMapping("/nseDashboard/reports/financial")
    public ResponseEntity<List<FinancialReport>> getFinancialReport(){
        return this.debtDashboardService.getFinancialReport();
    }

    @PostMapping("/nseDashboard/reports/general/adequacy")
    public ResponseEntity<List<AdequacyFullReport>> getAdequacyReport(@RequestHeader Map<String, String> header,
                                                               @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getAdequacyGeneralReport(header, reportRequest);
    }

    @PostMapping("/nseDashboard/reports/general/exceptional")
    public ResponseEntity<List<ExceptionalReport>> getExceptionalReport(@RequestHeader Map<String, String> header,
                                                                        @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getExceptionalReport(reportRequest, header);
    }

    @PostMapping("/nseDashboard/reports/general/soft-mail")
    public ResponseEntity<List<SoftMailReport>> getSoftMailReport(@RequestHeader Map<String, String> header,
                                                                  @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getSoftMailReport(reportRequest, header);
    }

    @PostMapping("/nseDashboard/reports/general/non-compliant")
    public ResponseEntity<List<NonComplianceReport>> getNonCompliantReport(@RequestHeader Map<String, String> header,
                                                                           @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getNonComplianceReport(reportRequest, header);
    }

    @PostMapping("/nseDashboard/reports/general/internal")
    public ResponseEntity<List<InternalReport>> getInternalReport(@RequestHeader Map<String, String> header,
                                                          @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getInternalReport(reportRequest, header);
    }

    @PostMapping("/nseDashboard/reports/general/wrong-subject")
    public ResponseEntity<List<WrongSubjectReport>> getWrongSubjectReport(@RequestHeader Map<String, String> header,
                                                          @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getWrongSubject(reportRequest, header);
    }

    @PostMapping("/nseDashboard/reports/general/clarification")
    public ResponseEntity<List<ClarificationGeneralReport>> getClarificationReport(@RequestHeader Map<String, String> header,
                                                          @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getClarificationReport(reportRequest, header);
    }

    @PostMapping("/nseDashboard/reports/general/announcement")
    public ResponseEntity<List<AnnouncementFullReport>> getAnnouncementReport(@RequestHeader Map<String, String> header,
                                                                @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getAnnouncementReport(reportRequest, header);
    }


    @PostMapping("/nseDashboard/report/general/auto-email")
    public ResponseEntity<List<AutoEmailReport>> getAutomatedEmailReport(@RequestHeader Map<String, String> header,
                                                                          @RequestBody GeneralReportRequest reportRequest){
        return this.debtDashboardService.getAutoEmailReport(reportRequest, header);
    }


    // -----------------------------------------------------------------------------------------------------------
    @GetMapping("/nseDashboard/reports/reg50")
    public ResponseEntity<List<Reg50>> getReg50(){
        return this.debtDashboardService.getReg50();
    }

    @GetMapping("/nseDashboard/reports/reg57")
    public ResponseEntity<List<Reg57>> getReg57(){
        return this.debtDashboardService.getReg57();
    }

    @GetMapping("/nseDashboard/reports/reg60")
    public ResponseEntity<List<Reg60>> getReg60(){
        return this.debtDashboardService.getReg60();
    }

    // -----------------------------------------------------------------------------------------------------------
    @GetMapping("/nseDashboard/report/watchlist")
    public ResponseEntity<List<Watchlist>> getAllWatchlist(@RequestHeader Map<String, String> header){
        return this.debtDashboardService.getAllWatchlist(header);
    }

    @PostMapping("/nseDashboard/report/watchlist")
    public ResponseEntity<Status> saveAllWatchlist(@RequestHeader Map<String, String> header,
                                                   @RequestBody WatchListRequest watchlist){
        return this.debtDashboardService.saveAllWatchlist(watchlist, header);
    }
}
