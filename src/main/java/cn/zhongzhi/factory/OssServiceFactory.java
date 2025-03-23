package cn.zhongzhi.factory;



import cn.zhongzhi.enums.OssTypeEnum;
import cn.zhongzhi.oss.OssService;
import cn.zhongzhi.oss.impl.MinioOssService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class OssServiceFactory {

    @Resource
    private MinioOssService minioOssService;

    public OssService getOssService(OssTypeEnum ossTypeEnum) {
        switch (ossTypeEnum)
            {
                case MINIO:
                    return minioOssService;
                default:
                    throw new IllegalArgumentException("Unsupported OSS type: " + ossTypeEnum);
            }
    }
}