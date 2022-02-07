package com.nse.listingcompliance.lcdebtannouncementservice.repository.attachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentMasterRepository extends JpaRepository<com.nse.listingcompliance.lcdebtannouncementservice.entity.docupload.AttachmentMaster, Long> {

}
