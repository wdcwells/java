package com.weiyan.cardloan.dubbo.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : wyx
 * @description :  订单状态枚举.
 * @date : create in 2018/3/26  下午6:51
 * @modified by :
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnumsEntry {

    AUDITING((byte) 0, "审核中 - 正在审核 (后台努力审核中 请耐心等待)", false),
    WAITING_GATHERING((byte) 1, "待收款 - 审核通过 --确认收款时间？)", false),
    WAITING_LENDING((byte) 2, "待放款 - 温馨提示 (你已确认借款 请等待放款)", false),
    WAITING_REPAY((byte) 3, "待还款 --显示当期还款信息？", true),
    IS_REPAYING((byte) 4, "正在还款（本次还款金额**元）", false),
    REPAY_FAIL((byte) 5, "还款中 - 还款失败 --弹窗-----------只显示一次", false),
    REPAY_OK((byte) 6, "还款中 - 还款成功 --弹窗-----------只显示一次", false),
    DETAIL_REPAYING((byte) 7, "还款中 - 正在结清（本次结清金额**元）", false),
    WAITING_REPAY_OVER_DUE((byte) 8, "已逾期 - 待还款", true),
    IS_REPAYING_OVER_DUE((byte) 9, "已逾期 - 正在逾期还款（本次扣款金额**元）", false),
    REPAY_OK_OVER_DUE((byte) 10, "已逾期 - 还款成功 --弹窗-----------只显示一次", false),
    REPAY_FAIL_OVER_DUE((byte) 11, "已逾期 - 还款失败 --弹窗-----------只显示一次", false),
    REJECT_LOW_LIMIT((byte) 91, "已拒绝 - 审核拒绝（信用额度较低）", false),
    REJECT_AUDIT((byte) 92, "已拒绝 - 审核拒绝（要款的审核拒绝）", false),
    REJECT_FATHERING_TIMEOUT((byte) 93, "已超时 - 收款失败（超出收款时间）", false),
    REJECT_FATHERING_ACCOUNT((byte) 94, "已取消 - 收款失败（账户原因）", false),
    REJECT_FATHERING_CANCEL((byte) 95, "已取消 - 收款失败（取消借款）", false),
    EARLY_SETTLEMENT((byte) 96, "已结清 - 提前结清（可继续申请借款）", false),
    SETTLEMENT((byte) 97, "已结清 - 全部结清（可继续申请借款）", false),;

    /**
     * 状态码
     */
    private Byte code;

    /**
     * 状态信息
     */
    private String msg;

    /**
     * 是否可以执行还款操作
     */
    private Boolean repayAble;

    public static OrderStatusEnumsEntry getEnumsByCode(byte code) {
        OrderStatusEnumsEntry[] values = OrderStatusEnumsEntry.values();
        for (OrderStatusEnumsEntry enums : values) {
            if (enums.getCode() == code) {
                return enums;
            }
        }
        return null;
    }
}
