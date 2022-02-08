package com.nse.listingcompliance.lcdebtannouncementservice.drools.impl;

import com.nse.listingcompliance.lcdebtannouncementservice.drools.classes.Adequacy;
import com.nse.listingcompliance.lcdebtannouncementservice.drools.classes.AutoEmail;
import com.nse.listingcompliance.lcdebtannouncementservice.drools.BusinessLogicInterface;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DroolsLogic implements BusinessLogicInterface {
    private final KieContainer kieContainer;

    @Autowired
    public DroolsLogic(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Override
    public Adequacy getAdequacy(Long categoryId, Long subjectTypeId) {
        KieSession kieSession = kieContainer.newKieSession();
        Adequacy adequacy = new Adequacy();
        adequacy.setSubjectTypeId(subjectTypeId);
        adequacy.setCategoryId(categoryId);
        kieSession.insert(adequacy);
        kieSession.fireAllRules();
        kieSession.dispose();
        return adequacy;
    }

    @Override
    public Boolean getAutoEmail(Long categoryId, Long subjectTypeId) {
        KieSession kieSession = kieContainer.newKieSession();
        AutoEmail autoEmail = new AutoEmail();
        autoEmail.setSubjectTypeId(subjectTypeId);
        autoEmail.setCategoryId(categoryId);
        kieSession.insert(autoEmail);
        kieSession.fireAllRules();
        kieSession.dispose();
        return autoEmail.getAutoEmailStatus();
    }
}
