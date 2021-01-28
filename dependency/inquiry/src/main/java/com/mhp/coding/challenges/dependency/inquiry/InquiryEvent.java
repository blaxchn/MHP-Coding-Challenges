package com.mhp.coding.challenges.dependency.inquiry;

import org.springframework.context.ApplicationEvent;

public class InquiryEvent extends ApplicationEvent {

    /**
     * The instance of inquiry, associated with this event.
     */
    private Inquiry inquiry;

    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     * @param inquiry an instance of the class Inquiry, wich is saved as an attribute of this event
     */
    public InquiryEvent(Object source, Inquiry inquiry) {
        super(source);
        this.inquiry = inquiry;
    }

    /**
     * A getter method for the instance of Inquiry
     */
    public Inquiry getInquiry() {
        return inquiry;
    }
}
