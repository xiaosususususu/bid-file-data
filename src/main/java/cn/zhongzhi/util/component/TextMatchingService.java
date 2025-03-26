package cn.zhongzhi.util.component;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.dfa.WordTree;
import cn.zhongzhi.config.properties.KeywordConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class TextMatchingService {
    private List<KeywordConfig.OuterGroup> outerGroups;

    @Autowired
    public TextMatchingService(KeywordConfig keywordConfig) {
        this.outerGroups = keywordConfig.getOuterGroups();
    }

    public KeywordConfig.ResultInnerGroup matchAndGetSchema(String outerText, String innerText) {
        for (KeywordConfig.OuterGroup outerGroup : outerGroups) {
            if (outerText.contains(outerGroup.getFileSetSchema())) {
                KeywordConfig.ResultInnerGroup resultInnerGroup = new KeywordConfig.ResultInnerGroup();
                resultInnerGroup.setFileItemSchema(outerGroup.getFileItemSchema());
                resultInnerGroup.setFileSetSchemaName(outerGroup.getFileSetSchema());
                List<String> fileItemSchemaNameList = new ArrayList<>();
                for (KeywordConfig.InnerGroup innerGroup : outerGroup.getFileItemSchema()) {
                    WordTree matchTree = new WordTree();
                    matchTree.addWords(innerGroup.getMatchKeywords());
                    List<String> matchAllList = matchTree.matchAll(innerText, -1, true, true);
                    List<String> matchAlls = matchAllList.stream().distinct().toList();
                    log.info("第一段关键词匹配的字符串:{}, 匹配数量:{}", matchAlls, matchAlls.size());
                    //总共提供的单词数量
                    int allWordSize = innerGroup.getMatchKeywords().size();
                    //匹配的单词数量
                    int matchSize = matchAlls.size();
                    //比较超过80%，即认为匹配成功
                    boolean isMatched = matchSize >= allWordSize * 0.8;
                    if (isMatched) {
                        List<String> excludeKeywords = innerGroup.getExcludeKeywords();
                        if (CollUtil.isEmpty(excludeKeywords)){
                            fileItemSchemaNameList.add(innerGroup.getFileItemSchemaName());
                        }
                        WordTree excludeTree = new WordTree();
                        excludeTree.addWords(innerGroup.getExcludeKeywords());
                        List<String> excludeMatchAllList = excludeTree.matchAll(innerText, -1, true, true);
                        List<String> excludeMatchAlls = excludeMatchAllList.stream().distinct().toList();
                        log.info("第二段关键词匹配的字符串:{}, 匹配数量:{}", excludeMatchAlls, excludeMatchAlls.size());
                        //总共提供的单词数量
                        int excludeAllWordSize = innerGroup.getExcludeKeywords().size();
                        //匹配的单词数量
                        int excludeMatchSize = excludeMatchAlls.size();
                        //超过40%，即认为匹配失败，必须在40%以下
                        boolean isExcluded = excludeMatchSize >= excludeAllWordSize * 0.4;
                        if (!isExcluded) {
                            fileItemSchemaNameList.add(innerGroup.getFileItemSchemaName());
                        }
                    }
                }
                if (CollUtil.isNotEmpty(fileItemSchemaNameList)) {
                    resultInnerGroup.setFileItemSchemaName(fileItemSchemaNameList);
                    return resultInnerGroup;
                }
            }
        }
        return null;
    }
}