package cn.ntshare.laboratory.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private Long id;

    private String title;

    private String detail;

    private int amount;
}
