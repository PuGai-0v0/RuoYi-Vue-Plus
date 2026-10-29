package org.dromara.mobile.domain.vo;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 高德地图 POI 视图对象
 */
@Data
public class AmapPoiVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** POI 名称 */
    private String name;

    /** 详细地址 */
    private String address;

    /** 经度 */
    private String longitude;

    /** 纬度 */
    private String latitude;

    /** 距离（米） */
    private String distance;
}
