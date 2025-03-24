package cn.zhongzhi.factory;


import cn.zhongzhi.enums.TableNameMapServiceEnum;
import cn.zhongzhi.service.IStructuralDataPublicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author suchengbo
 * @Date 2025/3/23 03:00
 */
@Component
public class StructuralDataOperationExecute {
    @Autowired
    private Map<String, IStructuralDataPublicInterface> structuralDataPublicInterfaceMap;

    /**
     * 执行方法
     * @param tableName 表名
     * @param structuralData 结构化数据
     * @param relationColumnData 关联字段数据
     */
    public void execute(String tableName, String structuralData,Map<String,String> relationColumnData){
        //得到枚举
        TableNameMapServiceEnum tableNameMapServiceEnum = TableNameMapServiceEnum.valueOf(tableName);
        //执行对应方法
        structuralDataPublicInterfaceMap.get(tableNameMapServiceEnum.getValue()).dataOperation(tableName, structuralData,relationColumnData);
    }
}
