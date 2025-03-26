package cn.zhongzhi.service.impl;

import cn.zhongzhi.domain.mongo.AiOcrDataStructureDocument;
import cn.zhongzhi.repository.AiOcrDataStructureDocumentRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AiOcrDataStructureDocumentService {

    @Resource
    private AiOcrDataStructureDocumentRepository repository;

    /**
     * 新增记录
     * @param document 要保存的文档
     * @return 保存后的文档
     */
    public AiOcrDataStructureDocument save(AiOcrDataStructureDocument document) {
        document.setCreateTime(LocalDateTime.now());
        document.setUpdateTime(LocalDateTime.now());
        return repository.save(document);
    }

    /**
     * 根据 ID 查询记录
     * @param id 记录的 ID
     * @return 可选的文档对象
     */
    public Optional<AiOcrDataStructureDocument> findById(String id) {
        return repository.findById(id);
    }

    /**
     * 根据投标人 ID 查询记录
     * @param bidderId 投标人 ID
     * @return 符合条件的文档列表
     */
    public List<AiOcrDataStructureDocument> findByBidderId(String bidderId) {
        return repository.findByBidderId(bidderId);
    }

    /**
     * 根据文件 ID 查询记录
     * @param fileId 文件 ID
     * @return 符合条件的文档列表
     */
    public List<AiOcrDataStructureDocument> findByFileId(String fileId) {
        return repository.findByFileId(fileId);
    }

    /**
     * 根据表名查询记录
     * @param tableName 表名
     * @return 符合条件的文档列表
     */
    public List<AiOcrDataStructureDocument> findByTableName(String tableName) {
        return repository.findByTableName(tableName);
    }

    /**
     * 查询所有记录
     * @return 所有文档列表
     */
    public List<AiOcrDataStructureDocument> findAll() {
        return repository.findAll();
    }

    /**
     * 更新记录
     * @param document 要更新的文档
     * @return 更新后的文档
     */
    public AiOcrDataStructureDocument update(AiOcrDataStructureDocument document) {
        Optional<AiOcrDataStructureDocument> optional = repository.findById(document.getId());
        if (optional.isPresent()) {
            AiOcrDataStructureDocument existingDocument = optional.get();
            existingDocument.setBidderId(document.getBidderId());
            existingDocument.setFileId(document.getFileId());
            existingDocument.setTableName(document.getTableName());
            existingDocument.setFileSetName(document.getFileSetName());
            existingDocument.setFileItemName(document.getFileItemName());
            existingDocument.setResult(document.getResult());
            existingDocument.setUpdateTime(LocalDateTime.now());
            return repository.save(existingDocument);
        }
        return null;
    }

    /**
     * 根据 ID 删除记录
     * @param id 记录的 ID
     */
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}