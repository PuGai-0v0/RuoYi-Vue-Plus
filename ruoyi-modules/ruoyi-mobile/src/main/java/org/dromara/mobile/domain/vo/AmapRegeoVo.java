package org.dromara.mobile.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 高德地图逆地理编码视图对象
 */
@Data
public class AmapRegeoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 结构化地址 */
    private String formattedAddress;

    /** 省份 */
    private String province;

    /** 城市 */
    private String city;

    /** 区县 */
    private String district;
}
