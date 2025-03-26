package cn.zhongzhi.service.impl;


import cn.zhongzhi.domain.Bidder;
import cn.zhongzhi.service.*;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author suchengbo
 * @Date 2025/3/25 09:15
 */
@Service
public class OrganizeDataServiceImpl implements IOrganizeDataService {
    @Resource
    private BidderService bidderService;
    @Resource
    private LegalRepresentativeService legalRepresentativeService;
    @Resource
    private BidWinningNoticeFirstService bidWinningNoticeFirstService;
    @Resource
    private ContractAgreementFirstService contractAgreementFirstService;
    @Resource
    private ProjectAcceptanceCertificateService projectAcceptanceCertificateService;

    @Override
    public List<Object> organizeFullData() {
        List<Bidder> bidders = bidderService.list();
        for (Bidder bidder : bidders) {
            //身份证
            Map<String, Object> idCardMap = legalRepresentativeService.organizeIdentityData(bidder.getId());
            //中标通知书
            List<Map<String, Object>> resultListMap = bidWinningNoticeFirstService.organizeBiwWinningData(bidder.getId());
            //合同协议书
            Map<String, Object> contractMap = contractAgreementFirstService.organizeContractData(bidder.getId());
        }

        return List.of();
    }
}
