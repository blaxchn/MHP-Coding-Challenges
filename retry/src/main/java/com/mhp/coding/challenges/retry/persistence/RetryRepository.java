package com.mhp.coding.challenges.retry.persistence;

import com.mhp.coding.challenges.retry.core.entities.RetryEmailNotification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RetryRepository extends MongoRepository<RetryEmailNotification, String> {

    /***
     * Finds all RetryEmailNotifications stored in the repository.
     *
     * @return a list of all RetryEmailNotifications
     */
    public List<RetryEmailNotification> findAll();

    /***
     * Either stores an RetryEmailNotification as a new entry in the repository,
     * or overrides it, if the containing EmailNotification is already stored.
     *
     * @param retryEmailNotification The RetryEmailNotification entity that shall to be stored.
     * @return
     */
    public RetryEmailNotification save(RetryEmailNotification retryEmailNotification);

    /***
     * Deletes the corresponding entry for the RetryEmailNotification.
     *
     * @param retryEmailNotification The RetryEmailNotification entity that shall be dropped.
     */
    public void delete(RetryEmailNotification retryEmailNotification);

}
