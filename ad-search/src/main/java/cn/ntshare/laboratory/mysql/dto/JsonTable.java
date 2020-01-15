package cn.ntshare.laboratory.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class JsonTable {

    private String tableName;

    private Integer level;

    private List<Column> insert;

    private List<Column> update;

    private List<Column> delete;

    @Data
    @AllArgsConstructor
    public class Column {
        private String column;
    }
}
