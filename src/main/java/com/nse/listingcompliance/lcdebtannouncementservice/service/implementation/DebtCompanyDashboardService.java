package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.companydashboard.CompanyDashboardRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.*;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtCompanyDashboardServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class DebtCompanyDashboardService implements DebtCompanyDashboardServiceInterface {
    private AnnouncementRepository announcementRepo;
    private CompanyDashboardRepository companyDashboardRepo;

    @Autowired
    public DebtCompanyDashboardService(AnnouncementRepository announcementRepo, CompanyDashboardRepository companyDashboardRepo) {
        this.announcementRepo = announcementRepo;
        this.companyDashboardRepo = companyDashboardRepo;
    }

    @Override
    public ResponseEntity<List<Announcement>> getCompanyAnnouncement(Map<String, String> header) {
        List<Announcement> allAnn = this.companyDashboardRepo.findAllCompanyAnnouncements(Long.valueOf(header.get(Constants.ENTITY_ID)));
        return ResponseEntity.ok()
                .body(allAnn);
    }

    @Override
    public ResponseEntity<List<Draft>> getCompanyDraft(Map<String, String> header) {
        List<Draft> allDraft = this.companyDashboardRepo.findDraftCompanyAnnouncements(Long.valueOf(header.get(Constants.ENTITY_ID)));
        return ResponseEntity.ok()
                .body(allDraft);
    }

    @Override
    public ResponseEntity<List<Discrepancy>> getCompanyDiscrepancy(Map<String, String> header) {
        List<Discrepancy> allDiscrepancy = new ArrayList<>();

        Discrepancy report = Discrepancy.builder()
                .applicationNumber("123")
                .subjectType("New designation")
                .submissionDate(new Date())
                .clarificationStatus("Unsatisfactory")
                .clarificationReceivedDate(new Date())
                .communicationType("Wrong Subject")
                .responseDate(new Date())
                .build();

        allDiscrepancy.add(report);
        allDiscrepancy.add(report);

        return ResponseEntity.ok()
                .body(allDiscrepancy);
    }

    @Override
    public ResponseEntity<List<Pending>> getCompanyPending(Map<String, String> header) {
        List<Pending> allPending = this.companyDashboardRepo.findPendingCompanyAnnouncements(Long.valueOf(header.get(Constants.ENTITY_ID)));
        return ResponseEntity.ok()
                .body(allPending);
    }

    @Override
    public ResponseEntity<Cards> getCards(Map<String, String> header){
        Long disseminated = this.announcementRepo.getCompanyAnnStatus(Long.valueOf(header.get(Constants.ENTITY_ID)), Constants.DISSEMINATED);
        Long drafted = this.announcementRepo.getCompanyAnnStatus(Long.valueOf(header.get(Constants.ENTITY_ID)), Constants.DRAFTED);
        Long responsePending = this.announcementRepo.getCompanyAnnStatus(Long.valueOf(header.get(Constants.ENTITY_ID)), Constants.PENDING);
        Long clarification = this.announcementRepo.getCompanyAnnStatus(Long.valueOf(header.get(Constants.ENTITY_ID)), Constants.CLARIFICATION);

        Cards card = Cards.builder()
                .clarificationRequested(clarification)
                .drafted(drafted)
                .disseminated(disseminated)
                .responsePending(responsePending)
                .build();

        return ResponseEntity.ok().body(card);

    }

}
