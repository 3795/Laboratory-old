package cn.ntshare.laboratory.index.adplan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2020/1/2
 */
@Data
@AllArgsConstructor
public class AdPlanObject {

    private Long planId;

    private Long userId;

    private Integer planStatus;

    private Date startDate;

    private Date endDate;

    public void update(AdPlanObject newObject) {

        if (null != newObject.getPlanId()) {
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getUserId()) {
            this.userId = newObject.getUserId();
        }
        if (null != newObject.getPlanStatus()) {
            this.planStatus = newObject.getPlanStatus();
        }
        if (null != newObject.getStartDate()) {
            this.startDate = newObject.getStartDate();
        }
        if (null != newObject.getEndDate()) {
            this.endDate = newObject.getEndDate();
        }
    }
}
