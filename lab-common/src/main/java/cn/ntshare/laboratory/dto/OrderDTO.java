package cn.ntshare.laboratory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    private String title;

    private String detail;

    private BigDecimal price;

    public OrderDTO(String title, String detail, BigDecimal price) {
        this.title = title;
        this.detail = detail;
        this.price = price;
    }
}
