package com.nse.listingcompliance.lcdebtannouncementservice.controller;

import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.announcement.IsinRequestBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.isin.IsinNoDetails;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.masterdata.MasterData;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.remarks.Status;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.security.SymbolResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtApplicationServiceInterface;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin
@Api(value = "LC Debt Service", tags = "Application")
@Slf4j
public class DebtApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(DebtApplicationController.class);
    private DebtApplicationServiceInterface debtService;

    @Autowired
    public DebtApplicationController(DebtApplicationServiceInterface debtService) {
        this.debtService = debtService;
    }

    @GetMapping("/application/masterData")
    public ResponseEntity<MasterData> subjects(@RequestParam String companyType, @RequestHeader Map<String, String> header) {
        header.forEach((key, value) ->
                logger.info(String.format("__HEADER DETAILS__ %s  %s", key, value))
        );
        return this.debtService.getSubjectsAndCategory(companyType, header);
    }


    @PostMapping("/application/{announcementUuid}/isin")
    public ResponseEntity<Status> saveIsin(@PathVariable("announcementUuid") String announcementUuid,
                                           @RequestBody List<IsinRequestBody> isin,
                                           @RequestHeader Map<String, String> header) {
        return this.debtService.saveIsin(announcementUuid, isin, header);
    }

    @GetMapping("/application/{announcementUuid}/isin")
    public ResponseEntity<List<IsinResponseBody>> getIsin(@PathVariable("announcementUuid") String announcementUuid, @RequestHeader Map<String, String> header){
        return this.debtService.getIsinByAnnouncementId(announcementUuid, header);
    }

    @GetMapping("/application/isin/get")
    public ResponseEntity<IsinNoDetails> getNeapsIsin(@RequestHeader Map<String, String> header) {
        return this.debtService.getNeapsIsin(header);
    }

    @GetMapping("/application/nse/company")
    public ResponseEntity<List<SymbolResponse>> getAllCompanyList(@Param(value = "catId") Long catId,
                                                                  @RequestHeader Map<String, String> header){
        return this.debtService.getAllCompany(catId, header);
    }
}
