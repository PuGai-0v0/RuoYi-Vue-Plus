package org.dromara.mobile.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 移动端演示支付-预支付返回对象
 */
@Data
public class DemoPayVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 订单号 */
    private String orderNo;

    /** 支付渠道 */
    private String channel;

    /** 金额 */
    private BigDecimal amount;

    /** 模拟支付参数（演示骨架：正式接入时为渠道返回的调起参数） */
    private String payParam;
}
