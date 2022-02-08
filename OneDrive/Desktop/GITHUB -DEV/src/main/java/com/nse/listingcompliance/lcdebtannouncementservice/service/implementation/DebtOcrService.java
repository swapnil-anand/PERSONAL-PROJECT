package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.constant.Constants;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrKeywords;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.Subjects;
import com.nse.listingcompliance.lcdebtannouncementservice.exceptions.CommonException;
import com.nse.listingcompliance.lcdebtannouncementservice.proxy.OcrService;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr.OcrAttachment;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr.OcrMasterTable;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.ocr.Keyword;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.ocr.OcrAnnouncementResponse;
import com.nse.listingcompliance.lcdebtannouncementservice.requestbody.ocr.OcrMainBody;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtOcrServiceInterface;

import java.time.LocalDateTime;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

@Service
public class DebtOcrService implements DebtOcrServiceInterface {
    public static final Logger log = LoggerFactory.getLogger(DebtOcrService.class);

    private OcrAttachment ocrService;
    private OcrMasterTable ocrMasterRepo;
    private OcrAttachment ocrAttachmentRepo;
    private AnnouncementRepository announcementRepo;
    private OcrService ocrProxy;

    @Autowired
    public DebtOcrService(OcrAttachment ocrService, OcrMasterTable ocrMasterRepo, OcrAttachment ocrAttachmentRepo, AnnouncementRepository announcementRepo, OcrService ocrProxy) {
        this.ocrService = ocrService;
        this.ocrMasterRepo = ocrMasterRepo;
        this.ocrAttachmentRepo = ocrAttachmentRepo;
        this.announcementRepo = announcementRepo;
        this.ocrProxy = ocrProxy;
    }

    @Override
    public ResponseEntity<ArrayList<OcrKeywordResponseBody>> getOcrKeywords(String announcementUuid){
        List<OcrKeywords> ocrResult = this.ocrService.findById(announcementUuid);
        ArrayList<OcrKeywordResponseBody> ocrResponseBody = new ArrayList<>();
        for (OcrKeywords ocrKeywords : ocrResult) {
            OcrKeywordResponseBody newOcr = OcrKeywordResponseBody.builder()
                    .keyword(ocrKeywords.getKeyword())
                    .keywordType(ocrKeywords.getKeywordType())
                    .pageNo(ocrKeywords.getPageNo())
                    .build();
            ocrResponseBody.add(newOcr);
        }
        return new ResponseEntity<>(ocrResponseBody, HttpStatus.OK);
    }

    private void addOcr(OcrMainBody ocr, String announcementUuid, Map<String, String> header){
        Announcements newAnn = this.announcementRepo.findByAnnId(announcementUuid);
        OcrMaster ocrMaster = OcrMaster.builder()
                .announcement(newAnn)
                .companyName(ocr.getOcr_announcements_response().getCompany_name())
                .fileUrl(ocr.getOcr_announcements_response().getFile_url())
                .description(ocr.getOcr_announcements_response().getDescription())
                .pdfText("NA")
                .pdfWasScanned(ocr.getOcr_announcements_response().getPdf_was_scanned())
                .subject(ocr.getOcr_announcements_response().getSubject())
                .summary(ocr.getOcr_announcements_response().getSummary())
                .createdBy(header.get(Constants.USER_ID))
                .createdDt(LocalDateTime.now())
                .updatedBy(header.get(Constants.USER_ID))
                .updatedDt(LocalDateTime.now())
                .build();
        try{
            this.ocrMasterRepo.save(ocrMaster);
        } catch (Exception exe) {
            log.error("ERROR SAVING OCR MASTER ");
        }

        List<Keyword> subjectKeyword = ocr.getOcr_announcements_response().getSubject_keywords_found();
        List<Keyword> negativeSubjectKeywords = ocr.getOcr_announcements_response().getNegative_subject_keywords_found();
        List<Keyword> negativeGenericKeyword = ocr.getOcr_announcements_response().getNegative_generic_keywords_found();
        List<String> subjectMissedKeyword = ocr.getOcr_announcements_response().getSubject_keywords_missed();

        for(String keyword : subjectMissedKeyword) {
            OcrKeywords newKeywords = OcrKeywords.builder()
                    .keyword(keyword)
                    .keywordType("missed")
                    .ocrMasterId(ocrMaster)
                    .build();
            this.ocrAttachmentRepo.save(newKeywords);
        }
        this.insertKeywords(negativeSubjectKeywords, ocrMaster, "sensitive");
        this.insertKeywords(negativeGenericKeyword, ocrMaster, "generic");

    }

    private void insertKeywords(List<Keyword> keywords, OcrMaster ocrMaster, String keywordType) {
        for(Keyword keyword : keywords) {
            for(String key : keyword.getKeyword()) {
                OcrKeywords newKeywords = OcrKeywords.builder()
                        .keyword(key)
                        .keywordType(keywordType)
                        .pageNo(keyword.getPage())
                        .ocrMasterId(ocrMaster)
                        .build();
                this.ocrAttachmentRepo.save(newKeywords);
            }
        }
    }

    @Override
    public ResponseEntity<String> addKeywords(MultipartFile file, String announcementId, Map<String, String> header) throws CommonException {
        Announcements announcement = this.announcementRepo.findByAnnId(announcementId);

        String symbol = "sample";
        String companyName = header.get(Constants.COMPANY_NAME);
        String subject = "Buyback";
        String description = "Sample Test for Announcement " + announcementId;

        OcrMainBody ocrProxyResponse = this.ocrProxy.upload(file,symbol, companyName, subject, description);
        if(!ocrProxyResponse.getExecution_status().equals("success")) {
            throw new CommonException(ocrProxyResponse.getExecution_status(), "DOCUMENT WAS NOT SCANNED", HttpStatus.BAD_GATEWAY);
        }
        this.printOcrDetails(ocrProxyResponse);
        this.addOcr(ocrProxyResponse, announcement.getAnnId(), header);
        return ResponseEntity.ok().body("true");
    }

    private void printOcrDetails(OcrMainBody ocr) {
        log.info(String.format("__OCR__ %s", ocr.getExecution_status()));
        log.info(String.format("__OCR__ %s", ocr.getExecution_status_code().toString()));
        OcrAnnouncementResponse ocrResponse = ocr.getOcr_announcements_response();
        log.info(String.format("__OCR__ %s", ocrResponse.getCompany_name()));
        log.info(String.format("__OCR__ %s", ocrResponse.getDescription()));
        log.info(String.format("__OCR__ %s", ocrResponse.getFile_url()));
        log.info(String.format("__OCR__ %s", ocrResponse.getSubject()));
        log.info(String.format("__OCR__ %s", ocrResponse.getSummary()));
        log.info(String.format("__OCR__ %s", ocrResponse.getSymbol()));
        log.info(String.format("__OCR__ %s", ocrResponse.getText_excel()));

        List<Keyword> keywords1 = ocrResponse.getNegative_generic_keywords_found();
        List<Keyword> keywords2 = ocrResponse.getNegative_subject_keywords_found();
        List<Keyword> keywords3 = ocrResponse.getSubject_keywords_found();
        List<String> keywords = ocrResponse.getSubject_keywords_missed();

        for(String key :keywords) {
            log.info(String.format("__OCR__ %s", key));
        }

        this.printKeywords(keywords1, "negative_generic_keyword");
        this.printKeywords(keywords2, "negative_subject_keyword");
        this.printKeywords(keywords3, "subject_keyword");
    }

    private void printKeywords(List<Keyword> keywords, String keywordType) {
        log.error(String.format("__OCR__ %s", keywordType));
        for(Keyword key : keywords) {
            for(String keys : key.getKeyword()) {
                log.info(String.format("__OCR__ %s , %s ", keys, key.getPage().toString()));
            }
        }
    }


}
