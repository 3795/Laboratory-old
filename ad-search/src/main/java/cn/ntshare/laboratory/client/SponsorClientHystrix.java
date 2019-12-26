package cn.ntshare.laboratory.client;

import cn.ntshare.laboratory.vo.AdPlan;
import cn.ntshare.laboratory.vo.CommonResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/12/26
 */
@Component
public class SponsorClientHystrix implements SponsorClient {

    @Override
    public CommonResponse<List<AdPlan>> getAdPlanByIds(Long userId, List<Long> ids) {
        return new CommonResponse<>(-1, "ad-sponsor error");
    }
}
