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
    legal_representative_back("legalRepresentativeServiceImpl"),
    bank_account_opening_license("bankAccountOpeningLicenseServiceImpl"),
    senior_engineer_certificate_first("seniorEngineerCertificateFirstServiceImpl"),
    senior_engineer_certificate_last("seniorEngineerCertificateLastServiceImpl"),
    bid_winning_notice_first("bidWinningNoticeFirstServiceImpl"),
    bid_winning_notice_last("bidWinningNoticeLastServiceImpl"),
    contract_agreement_cover("contractorInfoServiceImpl"),
    contract_agreement_first("contractAgreementFirstServiceImpl"),
    contract_agreement_second("contractAgreementSecondServiceImpl"),
    contract_agreement_last_basic_info("contractAgreementLastBasicInfoServiceImpl"),
    project_acceptance_certificate("projectAcceptanceCertificateServiceImpl")
    ;
    private final String value;
}
