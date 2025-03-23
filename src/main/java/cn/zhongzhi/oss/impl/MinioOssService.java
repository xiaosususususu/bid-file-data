package cn.zhongzhi.oss.impl;


import cn.zhongzhi.oss.OssService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.messages.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MinioOssService implements OssService {

    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.access-key}")
    private String accessKey;

    @Value("${minio.secret-key}")
    private String secretKey;

    @Value("${minio.bucket-name}")
    private String bucketName;

    // 常见图片后缀名
    private static final List<String> IMAGE_SUFFIXES = List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".tiff");

    @Override
    public void uploadFile(String objectName, InputStream inputStream) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();

            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(inputStream, inputStream.available(), -1)
                    .build();

            minioClient.putObject(putObjectArgs);
        } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            log.error("上传文件失败", e);
        }
    }

    @Override
    public InputStream downloadFile(String objectName) {
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();

            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .build();

            return minioClient.getObject(getObjectArgs);
        } catch (IOException | ServerException | InsufficientDataException | ErrorResponseException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
            log.error("下载文件失败", e);
            return null;
        }
    }

    /**
     * 扫描桶下所有的图片路径和txt文档路径
     *
     * @return 包含图片路径和txt文档路径的列表
     */
    @Override
    public Iterable<Result<Item>> scanImageAndTxtPaths() {
        List<String> paths = new ArrayList<>();
        try {
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(endpoint)
                    .credentials(accessKey, secretKey)
                    .build();

            return minioClient.listObjects(ListObjectsArgs.builder()
                    .bucket(bucketName)
                    // 递归列出所有对象
                    .recursive(true)
                    .build());

            /*for (Result<Item> result : results) {
                Item item = result.get();
                String objectName = item.objectName();
                if (isImage(objectName) || isTxt(objectName)) {
                    System.out.println(objectName);
                    paths.add(objectName);
                }
            }*/

        } catch (Exception e) {
            log.error("扫描所有图片和文档路径失败", e);
        }
        return null;
    }

    /**
     * 根据网络流读取txt文档内容
     *
     * @param objectName txt文档在桶中的对象名
     * @return txt文档的内容
     */
    @Override
    public String readTxtContent(String objectName) {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = downloadFile(objectName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            log.error("读取txt文档内容失败", e);
        }
        return content.toString();
    }


}