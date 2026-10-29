package org.dromara.mobile.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 高德地图 Web 服务配置
 */
@Data
@ConfigurationProperties(prefix = "mobile.map.amap")
public class AmapProperties {

    /** Web 服务 API key */
    private String webServiceKey;
}
