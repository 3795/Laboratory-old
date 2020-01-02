package cn.ntshare.laboratory.index.adunit;

import cn.ntshare.laboratory.index.adplan.AdPlanObject;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2020/1/2
 */
@Data
@AllArgsConstructor
public class AdUnitObject {

    private Long unitId;

    private Integer unitStatus;

    private Integer positionType;      // 推广单元类型

    private Long planId;

    private AdPlanObject adPlanObject;

    void update(AdUnitObject newObject) {

        if (null != newObject.getUnitId()) {
            this.unitId = newObject.getUnitId();
        }
        if (null != newObject.getUnitStatus()) {
            this.unitStatus = newObject.getUnitStatus();
        }
        if (null != newObject.getPositionType()) {
            this.positionType = newObject.getPositionType();
        }
        if (null != planId) {
            this.planId = newObject.getPlanId();
        }
        if (null != newObject.getAdPlanObject()) {
            this.adPlanObject = newObject.getAdPlanObject();
        }
    }

}
