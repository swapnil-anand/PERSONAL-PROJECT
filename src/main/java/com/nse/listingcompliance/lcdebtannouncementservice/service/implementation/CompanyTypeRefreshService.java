package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.logindefinition.CompanyType;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.security.CompanyMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.logindefinition.CompanyTypeRefreshRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.CompanyMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.DebtSecurityMasterRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.security.SecurityRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.SecurityIds;
import com.nse.listingcompliance.lcdebtannouncementservice.service.CompanyTypeRefreshServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyTypeRefreshService implements CompanyTypeRefreshServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(CompanyTypeRefreshService.class);
    private CompanyTypeRefreshRepository companyTypeRefreshRepo;
    private SecurityRepository companySecurityRepo;
    private DebtSecurityMasterRepository debtSecurityMasterRepo;
    private CompanyMasterRepository companyMaster;

    @Autowired
    public CompanyTypeRefreshService(CompanyTypeRefreshRepository companyTypeRefreshRepo, SecurityRepository companySecurityRepo, DebtSecurityMasterRepository debtSecurityMasterRepo, CompanyMasterRepository companyMaster) {
        this.companyTypeRefreshRepo = companyTypeRefreshRepo;
        this.companySecurityRepo = companySecurityRepo;
        this.debtSecurityMasterRepo = debtSecurityMasterRepo;
        this.companyMaster = companyMaster;
    }
    // every six hour cron format 0 0 0/6 1/1 * ? *
    // every one minute cron format 0 */1 * ? * *
    @Scheduled(cron = "0 0 */6 ? * *", zone = "IST")
    public void updateCompanyType(){
        List<CompanyType> allCompanies = this.companyTypeRefreshRepo.findAll();
        for(CompanyType com : allCompanies) {
            Long orgId = com.getOrgId();
            String issuerName = this.debtSecurityMasterRepo.findByDsm_cm_id(orgId);
            CompanyMaster companyMasterData = this.companyMaster.findCompanyMasterByCm_org_id(orgId);
            Long symbolId = companyMasterData.getCm_symbol_id();
            List<SecurityIds> allIds = this.companySecurityRepo.findAllIds(symbolId);
            for(SecurityIds sec : allIds) {
                Long companyType = this.getCompanyType(sec, issuerName);
                try{
                    this.companyTypeRefreshRepo.updateCompanyType(orgId, companyType);
                } catch (Exception exe) {
                    log.error(String.format("EXE %s", exe.toString()));
                }
            }
        }
    }

    private Long getCompanyType(SecurityIds security, String dsmIssuerName) {
        String secSeries = security.getSecSeries();
        String secStatus = security.getSecStatus();
        if(secSeries.equals("EQ") && secStatus.equals("L")){
            return 1L;
        } else if(secSeries.equals("MT") || dsmIssuerName.toLowerCase().contains("municipal")) {
            return 3L;
        } else {
            return 2L;
        }
    }
}