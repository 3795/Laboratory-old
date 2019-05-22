package cn.ntshare.laboratory.client;

import cn.ntshare.laboratory.dto.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "lab-order", fallback = OrderClientFallback.class)
@Primary
public interface OrderClient {

    @GetMapping(value = "/{id}")
    OrderDTO getMyOrder(@PathVariable("id") Long id);

    @PostMapping(value = "/")
    OrderDTO create(@RequestBody OrderDTO dto);


}

@Component
class OrderClientFallback implements OrderClient {

    public OrderDTO getMyOrder(Long id) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTitle("服务宕掉了");
        return orderDTO;
    }

    public OrderDTO create(OrderDTO dto) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setTitle("服务宕掉了");
        return orderDTO;
    }
}