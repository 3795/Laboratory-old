package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.dao.CustomerRepository;
import cn.ntshare.laboratory.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Service
public class CustomerServiceInCode {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    public Customer create(Customer customer) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setIsolationLevel(TransactionDefinition.ISOLATION_SERIALIZABLE);
        // 设置传播机制
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_SUPPORTS);
        TransactionStatus ts = transactionManager.getTransaction(def);

        try {
            customer = customerRepository.save(customer);
            transactionManager.commit(ts);
            return customer;
        } catch (Exception e) {
            transactionManager.rollback(ts);
            return null;
        }
    }
}
