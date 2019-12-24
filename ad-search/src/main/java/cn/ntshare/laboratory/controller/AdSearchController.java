package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.annotation.IgnoreResponseAdvice;
import cn.ntshare.laboratory.vo.AdPlan;
import cn.ntshare.laboratory.vo.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/12/24
 */
@RestController
@RequestMapping
public class AdSearchController {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 使用Ribbon调用服务
     * @param userId
     * @param ids
     * @return
     */
    @GetMapping("/ribbon")
    @IgnoreResponseAdvice
    public CommonResponse<List<AdPlan>> ribbon(@RequestParam("userId") Long userId,
                                               @RequestParam("ids") List<Long> ids) {
        String url = String.format("http://ad-sponsor/ad-sponsor/ad_plan?userId=%d&ids=%d", userId, ids.get(0));
        return (CommonResponse<List<AdPlan>>) restTemplate.getForEntity(url, CommonResponse.class).getBody();
    }
}
