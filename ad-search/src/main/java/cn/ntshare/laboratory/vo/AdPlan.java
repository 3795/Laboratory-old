package cn.ntshare.laboratory.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/12/24
 */
@Data
@AllArgsConstructor
public class AdPlan {
    private Long id;
    private Long userId;
    private String planName;
    private Integer planStatus;
    private Date startDate;
    private Date endDate;
    private Date createTime;
    private Date updateTime;
}
