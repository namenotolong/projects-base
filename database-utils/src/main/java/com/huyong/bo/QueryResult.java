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

    @Data
    @Accessors(chain = true)
    public static class ResultColumn {
        private String name;

        private String label;
    }
}
