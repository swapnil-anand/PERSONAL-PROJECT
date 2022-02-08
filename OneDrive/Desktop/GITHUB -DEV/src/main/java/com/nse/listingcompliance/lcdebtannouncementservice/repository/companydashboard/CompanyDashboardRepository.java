package com.nse.listingcompliance.lcdebtannouncementservice.repository.companydashboard;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.Announcement;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.Draft;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.Pending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyDashboardRepository extends JpaRepository<Announcements, String> {
    String COMPANY_DASHBOARD_RESPONSE = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.Announcement";
    String COMPANY_DASHBOARD_DRAFT_RESPONSE = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.Draft";
    String COMPANY_DASHBOARD_PENDING_RESPONSE = "new com.nse.listingcompliance.lcdebtannouncementservice.responsebody.companydashboard.Pending";

    @Query(
            value = "SELECT " + COMPANY_DASHBOARD_RESPONSE +
                    "(a.annId, c.categoryName, s.subjectName," +
                    " a.announcementText, a.announcementStatus, a.submissionDt," +
                    " a.broadcastDt, t.fileName ) " +
                    "FROM Announcements a " +
                    "INNER JOIN a.subjectTypeId s " +
                    "INNER JOIN s.category c " +
                    "INNER JOIN a.attachment t " +
                    "WHERE a.listingCompanyId = :listingCompanyId "
    )
    List<Announcement> findAllCompanyAnnouncements(@Param(value = "listingCompanyId") Long listingCompanyId);

    @Query(
            value = "SELECT " + COMPANY_DASHBOARD_DRAFT_RESPONSE +
                    "(a.annId, c.categoryName, s.subjectName," +
                    " a.announcementText, a.createdBy, a.updatedBy," +
                    " a.updatedDt, a.announcementStatus ) " +
                    "FROM Announcements a " +
                    "INNER JOIN a.subjectTypeId s " +
                    "INNER JOIN s.category c " +
                    "WHERE a.announcementStatus = 'DRAFT' and  a.listingCompanyId = :listingCompanyId "
    )
    List<Draft> findDraftCompanyAnnouncements(@Param(value = "listingCompanyId") Long listingCompanyId);

    @Query(
            value = "SELECT " + COMPANY_DASHBOARD_PENDING_RESPONSE +
                    "(a.annId, s.subjectName, a.submissionDt, c.createdDt, a.announcementText, c.emailType)" +
                    "FROM Announcements a " +
                    "INNER JOIN a.subjectTypeId s " +
                    "INNER JOIN a.communications c " +
                    "LEFT JOIN c.communicationResponse r " +
                    "WHERE a.listingCompanyId = :listingCompanyId AND c.createdBy IS NULL "
    )
    List<Pending> findPendingCompanyAnnouncements(@Param(value = "listingCompanyId") Long listingCompanyId);
}
