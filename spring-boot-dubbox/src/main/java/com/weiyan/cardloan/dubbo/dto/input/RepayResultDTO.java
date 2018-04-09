package com.weiyan.cardloan.dubbo.dto.input;

import lombok.Data;

import java.io.Serializable;

/**
 * @author : wyx
 * @description :  还款结果回执推送DTO.
 * @date : create in 2018/3/26  上午11:35
 * @modified by :
 */
@Data
public class RepayResultDTO implements Serializable {

    private static final long serialVersionUID = -8263446830462113382L;

    /**
     * 借据号（必）:默认传0
     */
    private Long loanId;

    /**
     * 还款结果（必）:N:失败Y:成功R:拒绝
     */
    private String isSuccess;

    /**
     * 还款日期（选）:YYYYMMDD格式
     */
    private String loanActvDt;

    /**
     * 状态（选）:  状态，默认传0
     */
    private Integer code;

    /**
     * 消息（选）: 反馈信息
     */
    private String msg;

    /**
     * 流水号（选）:  合作方唯一标识
     */
    private String uniCode;

    /**
     * 期数（选）: 默认传0
     */
    private Integer period;

}
