package cn.ntshare.laboratory.entity.unit_condition;

import lombok.Data;

import javax.persistence.*;

/**
 * 兴趣限制
 */
@Entity
@Table(name = "ad_unit_it")
@Data
public class AdUnitIt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "unit_id", nullable = false)
    private Long unitId;

    @Basic
    @Column(name = "it_tag", nullable = false)
    private String itTag;

    public AdUnitIt(Long unitId, String itTag) {
        this.unitId = unitId;
        this.itTag = itTag;
    }

}
