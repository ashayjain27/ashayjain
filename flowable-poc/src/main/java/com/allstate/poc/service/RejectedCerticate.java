package com.allstate.poc.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

/**
 *
 * In case of payment failure send rejection mail
 * @author A s H a Y
 */
public class RejectedCerticate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Payment not approved !!");
        System.out.println("Certificate should not get approved ");
    }
}
