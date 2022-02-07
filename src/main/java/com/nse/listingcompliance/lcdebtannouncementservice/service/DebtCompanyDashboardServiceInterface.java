package com.nse.listingcompliance.lcdebtannouncementservice.service;

import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DebtCompanyDashboardServiceInterface {
    ResponseEntity<List<Announcement>> getCompanyAnnouncement(Map<String, String> header);

    ResponseEntity<List<Draft>> getCompanyDraft(Map<String, String> header);

    ResponseEntity<List<Discrepancy>> getCompanyDiscrepancy(Map<String, String> header);

    ResponseEntity<List<Pending>> getCompanyPending(Map<String, String> header);

    ResponseEntity<Cards> getCards(Map<String, String> header);
}
