package com.weiyan.cardloan.dubbo.dto.input;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : wyx
 * @description :  还款RPC调用.
 * @date : create in 2018/3/26  下午2:53
 * @modified by :
 */
@Data
public class RepayOpsDTO implements Serializable {
    private static final long serialVersionUID = -8727707987503843227L;

    /**
     * 资产id
     */
    private String loan_id;

    /**
     * 实际还款金额（单位：分）
     */
    private Integer amount;

    /**
     * 还款类型：NORMAL(1, "正常还款"),OVERDUE(2, "逾期还款"),PRE(3, "提前还款"),
     */
    private String pay_type;

    /**
     * 还款时间：精确到天
     */
    private String repay_date;
}
