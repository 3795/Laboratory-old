package cn.ntshare.laboratory.client;

import cn.ntshare.laboratory.vo.AdPlan;
import cn.ntshare.laboratory.vo.CommonResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/12/26
 */
@FeignClient(value = "ad-sponsor", fallback = SponsorClientHystrix.class)
@Component
public interface SponsorClient {

    @GetMapping("/ad-sponsor/ad_plan")
    CommonResponse<List<AdPlan>> getAdPlanByIds(@RequestParam("userId") Long userId,
                                                @RequestParam("ids") List<Long> ids);
}
