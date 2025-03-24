package cn.zhongzhi.service;


import java.util.Map;

/**
 * @author suchengbo
 * @Date 2025/3/23 02:46
 */
public interface IStructuralDataPublicInterface {
    void dataOperation(String tableName, String jsonData, Map<String,String> relationColumnData);
}
