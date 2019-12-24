package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.vo.CreateUserRequest;
import cn.ntshare.laboratory.vo.CreateUserResponse;

public interface IUserService {

    /**
     * 创建用户
     * @param request
     * @return
     */
    CreateUserResponse createUser(CreateUserRequest request);
}
