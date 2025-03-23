package cn.zhongzhi.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author  suchengbo
 * @Date    2025/3/21 10:48
 */
@Getter
@AllArgsConstructor
public enum OssTypeEnum {
    MINIO("minio"),
    ALIYUN("aliyun"),
    TENCENT("tencent"),
    HUAWEI("huawei"),
    QINIU("qiniu");

    private final String value;

}
