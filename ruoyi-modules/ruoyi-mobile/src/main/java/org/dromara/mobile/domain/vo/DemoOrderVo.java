package org.dromara.mobile.domain.vo;

import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.mobile.domain.DemoOrder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 移动端演示订单视图对象
 */
@Data
@AutoMapper(target = DemoOrder.class)
public class DemoOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long orderId;

    /** 订单号 */
    private String orderNo;

    /** 商品主题 */
    private String subject;

    /** 订单金额 */
    private BigDecimal amount;

    /** 支付渠道（wechat微信 alipay支付宝） */
    private String channel;

    /** 订单状态（pending待支付 paid已支付 cancelled已取消） */
    private String status;

    /** 支付时间 */
    private LocalDateTime payTime;

    /** 创建时间 */
    private LocalDateTime createTime;
}
