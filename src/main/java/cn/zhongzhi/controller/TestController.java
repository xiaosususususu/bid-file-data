package cn.zhongzhi.controller;


import cn.zhongzhi.domain.LegalRepresentative;
import cn.zhongzhi.service.LegalRepresentativeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author suchengbo
 * @Date 2025/3/22 11:12
 */
@RestController
@RequestMapping("/test")  // 显式指定类路径
public class TestController {
    @Resource
    private LegalRepresentativeService legalRepresentativeService;
    @GetMapping("/testGet")  // 完整路径: /test/testGet
    public List<LegalRepresentative> testGet() {
        return legalRepresentativeService.list();
    }
}
