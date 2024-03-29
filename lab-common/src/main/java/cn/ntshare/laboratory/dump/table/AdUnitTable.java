package cn.ntshare.laboratory.dump.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Qinyi.
 */
@Data
@AllArgsConstructor
public class AdUnitTable {

    private Long unitId;
    private Integer unitStatus;
    private Integer positionType;

    private Long planId;
}
