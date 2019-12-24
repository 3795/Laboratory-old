package cn.ntshare.laboratory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Data
@AllArgsConstructor
public class AdPlanGetRequest {

    private Long userId;
    private List<Long> ids;

    public boolean validate() {

        return userId != null && !CollectionUtils.isEmpty(ids);
    }
}
