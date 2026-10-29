package org.dromara.mobile.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 移动端演示订单对象 mobile_demo_order
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mobile_demo_order")
public class DemoOrder extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId(value = "order_id")
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
}
