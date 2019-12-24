package cn.ntshare.laboratory.service.impl;

import cn.ntshare.laboratory.constant.CommonStatus;
import cn.ntshare.laboratory.constant.Constants;
import cn.ntshare.laboratory.dao.AdPlanRepository;
import cn.ntshare.laboratory.dao.AdUserRepository;
import cn.ntshare.laboratory.entity.AdPlan;
import cn.ntshare.laboratory.entity.AdUser;
import cn.ntshare.laboratory.exception.AdException;
import cn.ntshare.laboratory.service.IAdPlanService;
import cn.ntshare.laboratory.utils.CommonUtils;
import cn.ntshare.laboratory.vo.AdPlanGetRequest;
import cn.ntshare.laboratory.vo.AdPlanRequest;
import cn.ntshare.laboratory.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdPlanServiceImpl implements IAdPlanService {

    @Autowired
    private AdPlanRepository planRepository;

    @Autowired
    private AdUserRepository userRepository;

    @Override
    public AdPlanResponse createAdPlan(AdPlanRequest request) {
        if (!request.createValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        // 确保关联的 User 是存在的
        Optional<AdUser> adUser = userRepository.findById(request.getUserId());
        if (!adUser.isPresent()) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        AdPlan oldPlan = planRepository.findByUserIdAndPlanName(request.getUserId(), request.getPlanName());
        if (oldPlan != null) {
            throw new AdException(Constants.ErrorMsg.SAME_NAME_PLAN_ERROR);
        }

        AdPlan newAdPlan = planRepository.save(
                new AdPlan(request.getUserId(), request.getPlanName(),
                        CommonUtils.parseStringDate(request.getStartDate()),
                        CommonUtils.parseStringDate(request.getEndDate())
                )
        );

        return new AdPlanResponse(newAdPlan.getId(), newAdPlan.getPlanName());
    }

    @Override
    public List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) {
        if (!request.validate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        List<AdPlan> list = planRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
//        return planRepository.findAllByIdInAndUserId(request.getIds(), request.getUserId());
        return list;
    }

    @Override
    public AdPlanResponse updateAdPlan(AdPlanRequest request) {
        if (!request.updateValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        if (request.getPlanName() != null) {
            plan.setPlanName(request.getPlanName());
        }

        if (request.getStartDate() != null) {
            plan.setStartDate(CommonUtils.parseStringDate(request.getStartDate()));
        }

        if (request.getEndDate() != null) {
            plan.setEndDate(CommonUtils.parseStringDate(request.getEndDate()));
        }

        plan.setUpdateTime(new Date());
        plan = planRepository.save(plan);

        return new AdPlanResponse(plan.getId(), plan.getPlanName());
    }

    @Override
    public void deleteAdPlan(AdPlanRequest request) {
        if (!request.deleteValidate()) {
            throw new AdException(Constants.ErrorMsg.REQUEST_PARAM_ERROR);
        }

        AdPlan plan = planRepository.findByIdAndUserId(request.getId(), request.getUserId());
        if (plan == null) {
            throw new AdException(Constants.ErrorMsg.CAN_NOT_FIND_RECORD);
        }

        plan.setPlanStatus(CommonStatus.INVALID.getStatus());
        plan.setUpdateTime(new Date());
        planRepository.save(plan);
    }
}
