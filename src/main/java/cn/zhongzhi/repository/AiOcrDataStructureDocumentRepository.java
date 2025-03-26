package cn.zhongzhi.repository;

import cn.zhongzhi.domain.mongo.AiOcrDataStructureDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AiOcrDataStructureDocumentRepository extends MongoRepository<AiOcrDataStructureDocument, String> {

    // 根据投标人 ID 查询记录
    List<AiOcrDataStructureDocument> findByBidderId(String bidderId);

    // 根据文件 ID 查询记录
    List<AiOcrDataStructureDocument> findByFileId(String fileId);

    // 根据表名查询记录
    List<AiOcrDataStructureDocument> findByTableName(String tableName);
}