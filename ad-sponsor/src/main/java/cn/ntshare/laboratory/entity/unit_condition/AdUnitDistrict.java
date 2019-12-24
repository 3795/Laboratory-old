package cn.ntshare.laboratory.entity.unit_condition;

import lombok.Data;

import javax.persistence.*;

/**
 * 地域限制
 */
@Data
@Entity
@Table(name = "ad_unit_district")
public class AdUnitDistrict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Basic
    @Column(name = "province", nullable = false)
    private String province;        // 省份

    @Basic
    @Column(name = "city", nullable = false)
    private String city;        // 市区

    public AdUnitDistrict(Long unitId, String province, String city) {
        this.unitId = unitId;
        this.province = province;
        this.city = city;
    }

}
