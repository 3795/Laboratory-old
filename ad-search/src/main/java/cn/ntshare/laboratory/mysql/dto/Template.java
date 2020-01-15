package cn.ntshare.laboratory.mysql.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Template {
    private String database;
    private List<JsonTable> tableList;
}
