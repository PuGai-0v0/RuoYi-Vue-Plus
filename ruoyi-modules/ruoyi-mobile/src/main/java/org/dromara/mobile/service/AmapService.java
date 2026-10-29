package org.dromara.mobile.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.mobile.config.AmapProperties;
import org.dromara.mobile.domain.vo.AmapPoiVo;
import org.dromara.mobile.domain.vo.AmapRegeoVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 高德地图 Web 服务代理（key 配置于模块内 mobile.properties）
 */
@RequiredArgsConstructor
@Service
public class AmapService {

    private static final String BASE_URL = "https://restapi.amap.com/v3";

    private final AmapProperties amapProperties;

    /** 周边搜索 */
    public List<AmapPoiVo> aroundSearch(String keywords, String location, int radius) {
        String key = checkKey();
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("location", location);
        params.put("keywords", keywords);
        params.put("radius", radius);
        params.put("offset", 10);
        JSONObject json = request(BASE_URL + "/place/around", params);
        List<AmapPoiVo> result = new ArrayList<>();
        JSONArray pois = json.getJSONArray("pois");
        for (int i = 0; i < pois.size(); i++) {
            JSONObject p = pois.getJSONObject(i);
            AmapPoiVo vo = new AmapPoiVo();
            vo.setName(p.getStr("name"));
            vo.setAddress(p.getStr("address"));
            vo.setDistance(p.getStr("distance"));
            String loc = p.getStr("location");
            if (StrUtil.isNotBlank(loc) && loc.contains(",")) {
                vo.setLongitude(loc.split(",")[0]);
                vo.setLatitude(loc.split(",")[1]);
            }
            result.add(vo);
        }
        return result;
    }

    /** 逆地理编码 */
    public AmapRegeoVo regeo(String location) {
        String key = checkKey();
        Map<String, Object> params = new HashMap<>();
        params.put("key", key);
        params.put("location", location);
        JSONObject json = request(BASE_URL + "/geocode/regeo", params);
        JSONObject component = json.getJSONObject("regeocode").getJSONObject("addressComponent");
        AmapRegeoVo vo = new AmapRegeoVo();
        vo.setFormattedAddress(json.getJSONObject("regeocode").getStr("formatted_address"));
        vo.setProvince(component.getStr("province"));
        vo.setCity(component.getStr("city"));
        vo.setDistrict(component.getStr("district"));
        return vo;
    }

    private String checkKey() {
        if (StrUtil.isBlank(amapProperties.getWebServiceKey())) {
            throw new ServiceException("高德地图 web-service-key 未配置，请在 mobile.properties 中配置后重启");
        }
        return amapProperties.getWebServiceKey();
    }

    private JSONObject request(String url, Map<String, Object> params) {
        String body = HttpUtil.get(url, params, 5000);
        JSONObject json = JSONUtil.parseObj(body);
        if (!"1".equals(json.getStr("status"))) {
            throw new ServiceException("高德地图服务异常：" + json.getStr("info"));
        }
        return json;
    }
}
