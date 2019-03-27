package cn.ntshare.laboratory.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created By Seven.wk
 * Description: 用户模型
 * Created At 2018/11/06
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @ApiModelProperty(value = "ID", example = "132")
    private String id;

    @ApiModelProperty(value = "用户名", example = "小明")
    private String username;
}
