package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.dto.OrderDTO;

public interface UserService {

    boolean createOrder(OrderDTO orderDTO);

    boolean login(String username, String password);
}
