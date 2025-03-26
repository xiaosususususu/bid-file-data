package cn.zhongzhi.domain.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * @author suchengbo
 * @Date 2025/3/24 21:20
 */
@Builder
@Data
@Document(collection = "ai_ocr_data_structure_document")
@AllArgsConstructor
@NoArgsConstructor
public class AiOcrDataStructureDocument {
    @Id
    private String id;
    private String bidderId;
    private String fileId;
    private String tableName;
    private String fileSetName;
    private String fileItemName;
    private Object result;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
