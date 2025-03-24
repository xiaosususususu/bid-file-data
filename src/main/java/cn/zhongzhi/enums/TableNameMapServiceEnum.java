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
    legal_representative("legalRepresentativeServiceImpl"),
    legal_representative_back("legalRepresentativeBackServiceImpl"),
    bank_account_opening_license("bankAccountOpeningLicenseServiceImpl"),
    senior_engineer_certificate_first("seniorEngineerCertificateFirstServiceImpl"),
    senior_engineer_certificate_last("seniorEngineerCertificateLastServiceImpl"),
    bid_winning_notice_first("bidWinningNoticeFirstService"),
    bid_winning_notice_last("bidWinningNoticeLastService"),
    ;
    private final String value;
}
