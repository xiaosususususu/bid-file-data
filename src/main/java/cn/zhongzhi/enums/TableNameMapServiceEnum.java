package cn.zhongzhi.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author suchengbo
 * @Date 2025/3/23 03:09
 */
@AllArgsConstructor
@Getter
public enum TableNameMapServiceEnum {
    legal_representative("legalRepresentativeServiceImpl");
    private final String value;
}
