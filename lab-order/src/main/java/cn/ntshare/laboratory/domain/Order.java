package cn.ntshare.laboratory.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "customer_order")
@NoArgsConstructor
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String detail;

    private BigDecimal price;

}
