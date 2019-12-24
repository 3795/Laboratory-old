package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.entity.AdPlan;
import cn.ntshare.laboratory.service.IAdPlanService;
import cn.ntshare.laboratory.vo.AdPlanGetRequest;
import cn.ntshare.laboratory.vo.AdPlanRequest;
import cn.ntshare.laboratory.vo.AdPlanResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ad_plan")
public class OPAdPlanController {

    @Autowired
    private IAdPlanService adPlanService;

    @PostMapping
    public AdPlanResponse createAdPlan(@RequestBody AdPlanRequest request) {
        return adPlanService.createAdPlan(request);
    }

    @GetMapping
    public List<AdPlan> getAdPlanByIds(@RequestParam("userId") Long userId,
                                       @RequestParam("ids") List<Long> ids) {
        AdPlanGetRequest request = new AdPlanGetRequest(userId, ids);
        return adPlanService.getAdPlanByIds(request);
    }

    @PutMapping
    public AdPlanResponse updateAdPlan(@RequestBody AdPlanRequest request) {
        return adPlanService.updateAdPlan(request);
    }

    @DeleteMapping
    public void deleteAdPlan(@RequestBody AdPlanRequest request) {
        adPlanService.deleteAdPlan(request);
    }

}
