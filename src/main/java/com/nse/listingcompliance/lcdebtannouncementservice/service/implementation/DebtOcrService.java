package com.nse.listingcompliance.lcdebtannouncementservice.service.implementation;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.announcement.Announcements;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrKeywords;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.ocr.OcrMaster;
import com.nse.listingcompliance.lcdebtannouncementservice.entity.masterdata.Subjects;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.announcementandattachments.AnnouncementRepository;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr.OcrAttachment;
import com.nse.listingcompliance.lcdebtannouncementservice.repository.ocr.OcrMasterTable;
import com.nse.listingcompliance.lcdebtannouncementservice.responsebody.ocrkeyword.OcrKeywordResponseBody;
import com.nse.listingcompliance.lcdebtannouncementservice.service.DebtOcrServiceInterface;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class DebtOcrService implements DebtOcrServiceInterface {
    private OcrAttachment ocrService;
    private OcrMasterTable ocrMasterRepo;
    private OcrAttachment ocrAttachmentRepo;
    private AnnouncementRepository announcementRepo;

    @Autowired
    public DebtOcrService(OcrAttachment ocrService, OcrMasterTable ocrMasterRepo, OcrAttachment ocrAttachmentRepo, AnnouncementRepository announcementRepo) {
        this.ocrService = ocrService;
        this.ocrMasterRepo = ocrMasterRepo;
        this.ocrAttachmentRepo = ocrAttachmentRepo;
        this.announcementRepo = announcementRepo;
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

    @Override
    public void addOcr(String announcementUuid){
        Announcements newAnn = this.announcementRepo.findByAnnId(announcementUuid);
        Subjects subjects = newAnn.getSubjectTypeId();
            OcrMaster ocrMaster = OcrMaster.builder()
                    .announcement(newAnn)
                    .companyName("Static company Type")
                    .fileUrl("static")
                    .description("Sample")
                    .pdfText("sample")
                    .pdfWasScanned(true)
                    .subject(subjects.getSubjectName())
                    .summary("sample")
                    .build();
        this.ocrMasterRepo.save(ocrMaster);
        for(int j = 0; j < 1; j++ ){
            OcrKeywords newKeywords = OcrKeywords.builder()
                    .keyword("Pdf")
                    .keywordType("sensitive")
                    .pageNo(1L)
                    .ocrMasterId(ocrMaster)
                    .build();
            this.ocrAttachmentRepo.save(newKeywords);
        }
    }

}
