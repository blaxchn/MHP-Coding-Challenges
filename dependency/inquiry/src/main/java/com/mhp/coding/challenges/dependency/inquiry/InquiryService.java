package com.mhp.coding.challenges.dependency.inquiry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class InquiryService {

    private static final Logger LOG = LoggerFactory.getLogger(InquiryService.class);

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void create(final Inquiry inquiry) {
        LOG.info("User sent inquiry: {}", inquiry);

        // Create new instance of InquiryEvent. The constructor takes in
        InquiryEvent inquiryEvent = new InquiryEvent(this, inquiry);
        applicationEventPublisher.publishEvent(inquiryEvent);
    }

}
