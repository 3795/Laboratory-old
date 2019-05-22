package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.client.OrderClient;
import cn.ntshare.laboratory.dao.UserRepository;
import cn.ntshare.laboratory.domain.User;
import cn.ntshare.laboratory.dto.OrderDTO;
import cn.ntshare.laboratory.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderClient orderClient;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public Boolean createOrder(@RequestBody OrderDTO orderDTO) {
        return userService.createOrder(orderDTO);
    }

    @GetMapping("/info")
    public Map userInfo() {
        User user = userRepository.findOneByUsername("imooc");
        OrderDTO orderDTO = orderClient.getMyOrder(1L);
        Map result = new HashMap<String, Object>();
        result.put("user", user);
        result.put("order", orderDTO);
        return result;
    }
}
