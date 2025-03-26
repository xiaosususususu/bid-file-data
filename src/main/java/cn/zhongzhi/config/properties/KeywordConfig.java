package cn.zhongzhi.config.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author suchengbo
 */
@Component
@ConfigurationProperties(prefix = "keyword-config")
public class KeywordConfig {
    private List<OuterGroup> outerGroups;

    public List<OuterGroup> getOuterGroups() {
        return outerGroups;
    }

    public void setOuterGroups(List<OuterGroup> outerGroups) {
        this.outerGroups = outerGroups;
    }
    @Data
    public static class OuterGroup {
        private String fileSetSchema;
        private List<InnerGroup> fileItemSchema;

    }
    @Data
    public static class InnerGroup {
        private List<String> matchKeywords;
        private List<String> excludeKeywords;
        private String tableName;
        private String fileItemSchemaName;
        private String schema;

    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ResultInnerGroup{
        private String fileSetSchemaName;
        private List<String> fileItemSchemaName;
        private List<InnerGroup> fileItemSchema;
    }
}    