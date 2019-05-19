package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.domain.Customer;
import cn.ntshare.laboratory.service.CustomerServiceInAn;
import cn.ntshare.laboratory.service.CustomerServiceInCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController  {

    @Autowired
    private CustomerServiceInCode customerServiceInCode;        // 用代码的方式

    @Autowired
    private CustomerServiceInAn customerServiceInAn;        // 用注解的方式

    @PostMapping("/code")
    public Customer createInCode(@RequestBody Customer customer) {
        return customerServiceInCode.create(customer);
    }

    @PostMapping("/annotation")
    public Customer createInAn(@RequestBody Customer customer) {
        return customerServiceInAn.create(customer);
    }

    @GetMapping
    public List<Customer> getAll() {
        return customerServiceInAn.findAll();
    }
}
