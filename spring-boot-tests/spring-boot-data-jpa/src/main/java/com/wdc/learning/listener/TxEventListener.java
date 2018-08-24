package com.wdc.learning.listener;

import com.wdc.learning.entity.Customer;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author wdc
 * @date 2018/8/24
 */
@Component
public class TxEventListener {

    @TransactionalEventListener
    public void afterCommit(ApplicationEvent event) {
        Object source = event.getSource();
        if (source instanceof Customer)
            System.out.printf("customer with firstName(%s) is saved.%n", ((Customer) source).getFirstName());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void afterRollback(ApplicationEvent event) {
        Object source = event.getSource();
        if (source instanceof Customer)
            System.out.printf("rollback when dealing with customer(%s).%n", source.toString());
    }
}
