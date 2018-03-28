package com.weiyan.cardloan.dubbo.service;


import com.weiyan.cardloan.dubbo.demo.RequestResult;

/**
 * Created by wangxin on 2017/7/3.
 */
public interface OpenAssetService {
    String interfaceName = "com.weiyan.loan.open.api.rpc.service.OpenAssetService";

    // 生成借款记录
    RequestResult createLoan(String loanJson);

    RequestResult getRepayPlan(long loanId);

    // principleInterestStatus,要查询的状态集合，逗号分隔
    RequestResult queryRepayPlan(long creditSideId, String planRepayTimeBegin, String principleInterestStatus,
                                 int insureType, int pageNumber, int pageSize);

    RequestResult repay(long loanId, long amount, int repayType, String bankSeqNo);

    RequestResult calculatePrePay(long loanId);

    RequestResult checkLoan(String time, long loanId);

    RequestResult replaceRepayPlan(String repayPlanJson);

    RequestResult wash();

    RequestResult claim(long loanId, int period, String operateTime, int operateType, long operateResult,
                        String bankFlowNo);

    RequestResult repayResultPush(String repayResultPushVoJson);

    // 现在还款
    RequestResult offlineRepay(String payJson);

    // 中航还款计划
    RequestResult zHRepayPlan(long loanId);

    // 中航还款金额
    RequestResult zHCalculateRepayAmount(long loanId, String repayType, String startDate);

    RequestResult payAgent(String sideId, long loanId, int period, long amount, int flowType);

    RequestResult payOtherFeeAgent(long time);

    // 中航线上还款
    RequestResult addZhOnlineRepay(String date);

    RequestResult getYuecaiRepayPlan(long loanId, String queryTime);

    RequestResult queryRiskControl(String loanIdListJson);

    // 保存进件
    RequestResult createOrder(String orderJson);

    // 修改订单信息
    RequestResult updateOrder(String updateOrderInfoParamJson);

    // 获取订单信息
    RequestResult getOrder(String queryOrderJson);

    // 获取放款账户信息
    RequestResult getLendBankInfo(long orderId);

    RequestResult returnApply(String returnJson);

    RequestResult getLeftPrinciple(long loanId);

    RequestResult getExportInfo(String exportJson);

    // 邮件还款提醒
    RequestResult sendRemindRepay(String date);

    RequestResult getFirstRepayDate(String paramJson);

    //还款计划试算
    RequestResult getCalculationRepayPlan(String paramJson);

    RequestResult insure(long loanId, int insureType, String insuranceNo);
}
