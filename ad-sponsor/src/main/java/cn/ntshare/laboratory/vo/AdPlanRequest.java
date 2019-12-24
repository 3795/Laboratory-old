package cn.ntshare.laboratory.vo;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class AdPlanRequest {

    private Long id;

    private Long userId;

    private String planName;

    private String startDate;

    private String endDate;

    public AdPlanRequest(Long id, Long userId) {
        this.id = id;
        this.userId = userId;
    }

    public boolean createValidate() {
        return userId != null
                && !StringUtils.isEmpty(planName)
                && !StringUtils.isEmpty(startDate)
                && !StringUtils.isEmpty(endDate);
    }

    public boolean updateValidate() {

        return id != null && userId != null;
    }

    public boolean deleteValidate() {

        return id != null && userId != null;
    }
}
