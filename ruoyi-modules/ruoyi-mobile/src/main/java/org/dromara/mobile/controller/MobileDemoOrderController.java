package org.dromara.mobile.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.redis.annotation.RepeatSubmit;
import org.dromara.common.web.core.BaseController;
import org.dromara.mobile.domain.bo.DemoOrderBo;
import org.dromara.mobile.domain.vo.DemoOrderVo;
import org.dromara.mobile.service.IDemoOrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 移动端-演示订单
 */
@SaCheckLogin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/demo/order")
public class MobileDemoOrderController extends BaseController {

    private final IDemoOrderService demoOrderService;

    /**
     * 创建移动端演示订单
     *
     * @param bo 移动端演示订单
     * @return 移动端演示订单
     */
    @Log(title = "移动端演示订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping("/create")
    public R<DemoOrderVo> create(@Validated @RequestBody DemoOrderBo bo) {
        return R.ok(demoOrderService.createOrder(bo));
    }

    /**
     * 查询移动端演示订单分页列表
     *
     * @param pageQuery 分页参数
     * @return 移动端演示订单集合
     */
    @GetMapping("/list")
    public R<PageResult<DemoOrderVo>> list(PageQuery pageQuery) {
        return R.ok(demoOrderService.queryPageList(pageQuery));
    }
}
