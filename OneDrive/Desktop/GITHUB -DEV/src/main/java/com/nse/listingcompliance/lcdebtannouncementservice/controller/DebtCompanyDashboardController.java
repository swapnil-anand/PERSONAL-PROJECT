package com.nse.listingcompliance.lcdebtannouncementservice.controller;

import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.*;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtCompanyDashboardServiceInterface;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "Company Dashboard")
@Slf4j
public class DebtCompanyDashboardController {
    private DebtCompanyDashboardServiceInterface debtCompanyDashboardService;

    @Autowired
    public DebtCompanyDashboardController(DebtCompanyDashboardServiceInterface debtCompanyDashboardService) {
        this.debtCompanyDashboardService = debtCompanyDashboardService;
    }

    @GetMapping("/companyDashboard/announcements")
    public ResponseEntity<List<Announcement>> getCompanyAnnouncement(@RequestHeader Map<String, String> header){
        return this.debtCompanyDashboardService.getCompanyAnnouncement(header);
    }

    @GetMapping("/companyDashboard/drafts")
    public ResponseEntity<List<Draft>> getCompanyDraft(@RequestHeader Map<String, String> header){
        return this.debtCompanyDashboardService.getCompanyDraft(header);
    }

    @GetMapping("/companyDashboard/discrepancy")
    public ResponseEntity<List<Discrepancy>> getCompanyDiscrepancy(@RequestHeader Map<String, String> header){
        return this.debtCompanyDashboardService.getCompanyDiscrepancy(header);
    }

    @GetMapping("/companyDashboard/pending")
    public ResponseEntity<List<Pending>> getCompanyPending(@RequestHeader Map<String, String> header){
        return this.debtCompanyDashboardService.getCompanyPending(header);
    }

    @GetMapping("/companyDashboard/kpi")
    public ResponseEntity<Cards> getCards(@RequestHeader Map<String, String> header){
        return this.debtCompanyDashboardService.getCards(header);
    }
}
