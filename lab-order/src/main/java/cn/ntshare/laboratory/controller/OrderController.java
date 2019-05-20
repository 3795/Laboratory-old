package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.dao.OrderRepository;
import cn.ntshare.laboratory.domain.Order;
import cn.ntshare.laboratory.dto.OrderDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping
    public OrderDTO create(@RequestBody OrderDTO orderDTO) {
        Order order = new Order();
        order.setAmount(orderDTO.getAmount());
        order.setDetail(orderDTO.getDetail());
        order.setTitle(orderDTO.getTitle());
        order = orderRepository.save(order);
        orderDTO.setId(order.getId());
        return orderDTO;
    }

    @GetMapping("/{id}")
    public OrderDTO getMyOrder(@PathVariable("id") Long id) {
        Optional<Order> result = orderRepository.findById(id);
        Order order = result.get();
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(order, orderDTO);
        return orderDTO;
    }

    @GetMapping
    public List<Order> getAll() {
        return orderRepository.findAll();
    }
}
