package cn.zhongzhi.test;


import cn.hutool.core.util.IdUtil;
import cn.zhongzhi.domain.mongo.AiOcrDataStructureDocument;
import cn.zhongzhi.service.impl.AiOcrDataStructureDocumentService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author suchengbo
 * @Date 2025/3/24 21:31
 */
@SpringBootTest
public class MongoTest {
    @Resource
    private AiOcrDataStructureDocumentService aiOcrDataStructureDocumentService;

    @Test
    public void testInsertMongoDocument() {
        AiOcrDataStructureDocument aiOcrDataStructureDocument = new AiOcrDataStructureDocument();
        aiOcrDataStructureDocument.setId(IdUtil.fastUUID());
        aiOcrDataStructureDocument.setBidderId("weqweqw");
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("name", "张三");
        aiOcrDataStructureDocument.setResult(objectObjectHashMap);
        aiOcrDataStructureDocumentService.save(aiOcrDataStructureDocument);
    }
}
