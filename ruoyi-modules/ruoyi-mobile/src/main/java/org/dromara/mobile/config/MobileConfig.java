package org.dromara.mobile.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 移动端模块配置：加载模块自带 mobile.properties（避免修改主框架配置）
 */
@Configuration
@PropertySource("classpath:mobile.properties")
@EnableConfigurationProperties({PayProperties.class, AmapProperties.class})
public class MobileConfig {
}
