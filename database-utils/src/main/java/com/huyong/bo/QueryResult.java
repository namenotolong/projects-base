package com.huyong.bo;

import com.alibaba.fastjson2.JSONObject;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class QueryResult {

    private List<JSONObject> tableData;

    private List<ResultColumn> tableMeta;

    private String executeSql;

    private boolean success;

    private String errorMsg;

    private Long count;

    @Data
    @Accessors(chain = true)
    public static class ResultColumn {
        private String name;

        private String label;

        private String type;
    }
}
