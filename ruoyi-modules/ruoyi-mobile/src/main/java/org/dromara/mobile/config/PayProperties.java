package org.dromara.mobile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 移动端支付渠道占位配置（实际开通需商户认证与 HTTPS 备案域名）
 */
@Data
@ConfigurationProperties(prefix = "mobile.pay")
public class PayProperties {

    private Channel wechat = new Channel();

    private Channel alipay = new Channel();

    @Data
    public static class Channel {
        private boolean enabled;
        private String appId;
        private String mchId;
        private String apiV3Key;
        private String privateKey;
    }
}
