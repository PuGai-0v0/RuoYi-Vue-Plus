package org.dromara.mobile.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.web.core.BaseController;
import org.dromara.mobile.domain.vo.AmapPoiVo;
import org.dromara.mobile.domain.vo.AmapRegeoVo;
import org.dromara.mobile.service.AmapService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 移动端-地图服务（代理高德 Web 服务）
 */
@SaCheckLogin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/demo/map")
public class MobileMapController extends BaseController {

    private final AmapService amapService;

    /** 周边 POI 搜索（location 格式：经度,纬度） */
    @GetMapping("/poi/around")
    public R<List<AmapPoiVo>> around(
        @RequestParam String keywords,
        @RequestParam String location,
        @RequestParam(defaultValue = "1000") Integer radius
    ) {
        return R.ok(amapService.aroundSearch(keywords, location, radius));
    }

    /** 逆地理编码（location 格式：经度,纬度） */
    @GetMapping("/regeo")
    public R<AmapRegeoVo> regeo(@RequestParam String location) {
        return R.ok(amapService.regeo(location));
    }
}
