package cn.ntshare.laboratory.service.impl;

import cn.ntshare.laboratory.constant.Constants;
import cn.ntshare.laboratory.dao.AdUserRepository;
import cn.ntshare.laboratory.entity.AdUser;
import cn.ntshare.laboratory.exception.AdException;
import cn.ntshare.laboratory.service.IUserService;
import cn.ntshare.laboratory.utils.CommonUtils;
import cn.ntshare.laboratory.vo.CreateUserRequest;
import cn.ntshare.laboratory.vo.CreateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private AdUserRepository adUserRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdUser oldUser = adUserRepository.
                findByUsername(request.getUsername());
        if (oldUser != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_ERROR);
        }

        AdUser newUser = adUserRepository.save(new AdUser(request.getUsername(), CommonUtils.md5(request.getUsername())));

        return new CreateUserResponse(newUser.getId(), newUser.getUsername(), newUser.getToken(), newUser.getCreateTime(), newUser.getUpdateTime());
    }
}
