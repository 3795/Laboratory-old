package cn.ntshare.laboratory.mysql.dto;

import cn.ntshare.laboratory.mysql.constant.OpType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
public class TableTemplate {

    private String tableName;

    private String level;

    private Map<OpType, List<String>> opTypeFieldSetMap = new HashMap<>();

    // 字段索引 -> 字段名
    private Map<Integer, String> posMap = new HashMap<>();

    public TableTemplate() {
    }
}
