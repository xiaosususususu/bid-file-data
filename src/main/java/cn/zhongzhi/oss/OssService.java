package cn.zhongzhi.oss;


import io.minio.Result;
import io.minio.messages.Item;

import java.io.InputStream;
import java.util.List;

/**
 * @author suchengbo
 * @Date 2025/3/21 10:26
 */
public interface OssService {
    /**
     * 文件上传
     * @param objectName 文件名
     * @param inputStream 流
     */
    void uploadFile(String objectName, InputStream inputStream);

    /**
     * 文件下载
     * @param objectName 文件名
     * @return 流
     */
    InputStream downloadFile(String objectName);

    /**
     * 扫描所有的txt和图片路径
     * @return 返回路径
     */
    Iterable<Result<Item>> scanImageAndTxtPaths();

    /**
     * 读取文本内容
     * @param objectName 文件名称
     * @return 返回文本值
     */
    String readTxtContent(String objectName);
}
