package com.nse.listingcompliance.lcdebtannouncementservice.repository.communications;

import com.nse.listingcompliance.lcdebtannouncementservice.entity.communication.CommunicationResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunicationResponseRepository extends JpaRepository<CommunicationResponse, Long> {
}
