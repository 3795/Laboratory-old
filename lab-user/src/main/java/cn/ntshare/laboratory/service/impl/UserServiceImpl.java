package cn.ntshare.laboratory.service.impl;

import cn.ntshare.laboratory.client.OrderClient;
import cn.ntshare.laboratory.dao.UserRepository;
import cn.ntshare.laboratory.domain.User;
import cn.ntshare.laboratory.dto.OrderDTO;
import cn.ntshare.laboratory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderClient orderClient;

    @Transactional
    public boolean createOrder(OrderDTO orderDTO) {
        User user = userRepository.findOneByUsername("imooc");
        BigDecimal amount = user.getAmount().subtract(orderDTO.getPrice());
        user.setAmount(amount);
        userRepository.save(user);
        orderClient.create(orderDTO);
        return true;
    }

    public boolean login(String username, String password) {
        return userRepository.findOneByUsernameAndPassword(username, password) != null;
    }
}
