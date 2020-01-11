package cn.ntshare.laboratory.controller;

import cn.ntshare.laboratory.annotation.IgnoreResponseAdvice;
import cn.ntshare.laboratory.client.SponsorClient;
import cn.ntshare.laboratory.vo.AdPlan;
import cn.ntshare.laboratory.vo.CommonResponse;
import com.github.shyiko.mysql.binlog.BinaryLogClient;
import com.github.shyiko.mysql.binlog.event.DeleteRowsEventData;
import com.github.shyiko.mysql.binlog.event.EventData;
import com.github.shyiko.mysql.binlog.event.UpdateRowsEventData;
import com.github.shyiko.mysql.binlog.event.WriteRowsEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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

    @Autowired
    private SponsorClient sponsorClient;

    /**
     * 使用Ribbon调用服务
     *
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

    /**
     * 使用feign调用微服务
     *
     * @param userId
     * @param ids
     * @return
     */
    @GetMapping("/feign")
    @IgnoreResponseAdvice
    public CommonResponse<List<AdPlan>> feign(@RequestParam("userId") Long userId,
                                              @RequestParam("ids") List<Long> ids) {
        return sponsorClient.getAdPlanByIds(userId, ids);
    }

    public static void main(String[] args) throws IOException {
        BinaryLogClient client = new BinaryLogClient("127.0.0.1", 3306, "root", "root");
        client.registerEventListener(event -> {
            EventData data = event.getData();
            if (data instanceof UpdateRowsEventData) {
                System.out.println("Update: " + data.toString());
            } else if (data instanceof WriteRowsEventData) {
                System.out.println("Write: " + data.toString());
            } else if (data instanceof DeleteRowsEventData) {
                System.out.println("Delete: " + data.toString());
            }
        });
        client.connect();
    }
}
