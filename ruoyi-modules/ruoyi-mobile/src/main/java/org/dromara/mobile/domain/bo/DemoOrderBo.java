package org.dromara.mobile.domain.bo;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 移动端演示订单业务对象（创建订单）
 */
@Data
public class DemoOrderBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 商品主题 */
    @NotBlank(message = "商品主题不能为空")
    private String subject;
}
