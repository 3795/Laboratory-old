package cn.ntshare.laboratory.service;

import cn.ntshare.laboratory.entity.AdPlan;
import cn.ntshare.laboratory.vo.AdPlanGetRequest;
import cn.ntshare.laboratory.vo.AdPlanRequest;
import cn.ntshare.laboratory.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {

    /**
     * 创建推广计划
     * @param request
     * @return
     */
    AdPlanResponse createAdPlan(AdPlanRequest request);

    /**
     * 获取推广计划
     * @param request
     * @return
     */
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request);

    /**
     * 更新推广计划
     * @param request
     * @return
     */
    AdPlanResponse updateAdPlan(AdPlanRequest request);

    /**
     * 删除推广计划
     * @param request
     */
    void deleteAdPlan(AdPlanRequest request);
}
