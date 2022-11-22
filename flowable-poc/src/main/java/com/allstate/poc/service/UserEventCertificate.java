package com.allstate.poc.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

/**
 * Handler method to send approved certificate
 * @author A s H a Y
 */
@Service
public class UserEventCertificate  implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {

        System.out.println("Payment approved!!");
        System.out.println("Sent valid certicates");

    }
}
