package cn.zhongzhi.test;


import cn.zhongzhi.task.InitBidFileDataTask;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author suchengbo
 * @Date 2025/3/24 22:12
 */
@SpringBootTest
public class ScannerAllFileDataTest {
    @Resource
    private InitBidFileDataTask initBidFileDataTask;

    @Test
    public void testScannerAllFileData() {
        initBidFileDataTask.getAllFileData();
    }
}
