package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findOneByUsername(String username);
}
