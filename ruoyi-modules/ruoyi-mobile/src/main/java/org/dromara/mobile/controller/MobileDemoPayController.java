package org.dromara.mobile.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.redis.annotation.RepeatSubmit;
import org.dromara.common.web.core.BaseController;
import org.dromara.mobile.domain.vo.DemoPayVo;
import org.dromara.mobile.service.IDemoOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 移动端-演示支付（骨架：渠道凭证未配置时返回明确提示）
 */
@SaCheckLogin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/demo/pay")
public class MobileDemoPayController extends BaseController {

    private final IDemoOrderService demoOrderService;

    /**
     * 订单支付
     *
     * @param orderNo 订单号
     * @param channel 支付渠道
     * @return 支付信息
     */
    @Log(title = "移动端演示支付", businessType = BusinessType.OTHER)
    @RepeatSubmit()
    @PostMapping("/prepay")
    public R<DemoPayVo> prepay(@RequestParam String orderNo, @RequestParam String channel) {
        return R.ok(demoOrderService.prepay(orderNo, channel));
    }
}
