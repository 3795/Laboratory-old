package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.domain.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void testFindOneByTitle() {
        Order order = orderRepository.findOneByTitle("test");
        System.out.println(order.getTitle());
    }

}