package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.dao.CustomerRepository;
import cn.ntshare.laboratory.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceInAn {

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
