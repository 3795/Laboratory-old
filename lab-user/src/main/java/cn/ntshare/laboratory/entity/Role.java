package cn.ntshare.laboratory.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Role {

    private Integer id;

    private String role;

    private String desc;
}
