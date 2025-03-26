package cn.zhongzhi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suchengbo
 * @Date 2025/3/25 09:10
 */
@RestController
@RequestMapping("/organize")
public class OrganizeDataController {
    /**
     * 组织全量数据
     * @return 返回值
     */
    @GetMapping("/fullData")
    public List<Object> testGet() {
        return null;
    }
}
