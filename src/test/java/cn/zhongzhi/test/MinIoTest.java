package cn.zhongzhi.test;


import cn.zhongzhi.oss.impl.MinioOssService;
import io.minio.Result;
import io.minio.messages.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


/**
 * @author suchengbo
 * @Date 2025/3/21 10:54
 */
@SpringBootTest
public class MinIoTest {

    @Autowired
    private MinioOssService minioOssService;

    @Value("${minio.bucket-name}")
    private String bucketName;
    // 常见图片后缀名
    private static final List<String> IMAGE_SUFFIXES = List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".tiff");

    @Test
    public void testScanImageAndTxtPaths() throws Exception {

        // 调用被测试的方法
        Iterable<Result<Item>> resultIterable = minioOssService.scanImageAndTxtPaths();
        for (Result<Item> result : resultIterable) {
            Item item = result.get();
            String objectName = item.objectName();
            if (isImage(objectName) || isTxt(objectName)) {
                System.out.println(objectName);

            }
        }
        // 可以根据实际情况添加更多的验证逻辑，例如验证路径的数量和内容
    }

    private boolean isImage(String objectName) {
        return IMAGE_SUFFIXES.stream()
                .anyMatch(suffix -> objectName.toLowerCase().endsWith(suffix));
    }

    private boolean isTxt(String objectName) {
        return objectName.toLowerCase().endsWith(".txt");
    }
}
