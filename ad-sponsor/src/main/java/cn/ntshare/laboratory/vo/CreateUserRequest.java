package cn.ntshare.laboratory.vo;

import lombok.Data;
import org.apache.commons.lang.StringUtils;

@Data
public class CreateUserRequest {

    private String username;

    public boolean validate() {
        return !StringUtils.isEmpty(username);
    }
}
