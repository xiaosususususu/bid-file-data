package cn.zhongzhi.service;


import cn.hutool.json.JSONObject;

import java.util.Map;

/**
 * @author suchengbo
 * @Date 2025/3/23 02:46
 */
public interface StructuralDataPublicInterface {
    void dataOperation(String tableName, String jsonData, Map<String,String> relationColumnData);
}
