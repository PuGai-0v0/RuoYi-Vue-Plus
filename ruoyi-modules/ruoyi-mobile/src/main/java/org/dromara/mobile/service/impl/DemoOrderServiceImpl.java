package org.dromara.mobile.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.mobile.config.PayProperties;
import org.dromara.mobile.domain.DemoOrder;
import org.dromara.mobile.domain.bo.DemoOrderBo;
import org.dromara.mobile.domain.vo.DemoOrderVo;
import org.dromara.mobile.domain.vo.DemoPayVo;
import org.dromara.mobile.mapper.DemoOrderMapper;
import org.dromara.mobile.service.IDemoOrderService;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 移动端演示订单 Service 业务层处理
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DemoOrderServiceImpl implements IDemoOrderService {

    private final DemoOrderMapper baseMapper;

    private final PayProperties payProperties;

    /** 演示商品固定金额 */
    private static final BigDecimal DEMO_AMOUNT = new BigDecimal("0.01");

    /**
     * 创建移动端演示订单
     *
     * @param bo 移动端演示订单
     * @return 移动端演示订单
     */
    @Override
    public DemoOrderVo createOrder(DemoOrderBo bo) {
        DemoOrder order = new DemoOrder();
        order.setOrderNo("MD" + DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS").format(LocalDateTime.now())
            + RandomUtil.randomInt(1000, 9999));
        order.setSubject(bo.getSubject());
        order.setAmount(DEMO_AMOUNT);
        order.setStatus("pending");
        baseMapper.insert(order);
        return baseMapper.selectVoById(order.getOrderId());
    }

    /**
     * 查询移动端演示订单分页列表
     *
     * @param pageQuery 分页参数
     * @return 移动端演示订单集合
     */
    @Override
    public PageResult<DemoOrderVo> queryPageList(PageQuery pageQuery) {
        LambdaQueryWrapper<DemoOrder> lqw = Wrappers.lambdaQuery(DemoOrder.class)
            .eq(DemoOrder::getCreateBy, LoginHelper.getUserId())
            .orderByDesc(DemoOrder::getCreateTime);
        IPage<DemoOrderVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(page.getRecords(), page.getTotal());
    }

    /**
     * 查询移动端演示订单
     *
     * @param orderNo 订单号
     * @return 移动端演示订单
     */
    @Override
    public DemoOrder getByOrderNo(String orderNo) {
        return baseMapper.selectOne(Wrappers.lambdaQuery(DemoOrder.class).eq(DemoOrder::getOrderNo, orderNo));
    }

    /**
     * 订单支付
     *
     * @param orderNo 订单号
     * @param channel 支付渠道
     * @return 支付信息
     */
    @Override
    public DemoPayVo prepay(String orderNo, String channel) {
        DemoOrder order = getByOrderNo(orderNo);
        if (order == null) {
            throw new ServiceException("订单不存在");
        }
        if (!"pending".equals(order.getStatus())) {
            throw new ServiceException("订单状态不允许支付");
        }
        PayProperties.Channel config = "wechat".equals(channel) ? payProperties.getWechat() : payProperties.getAlipay();
        if (!config.isEnabled()) {
            String name = "wechat".equals(channel) ? "微信支付" : "支付宝";
            throw new ServiceException(name + "渠道未开通：需完成商户认证与 HTTPS 备案，并在 mobile.properties 中配置后生效");
        }
        // 渠道已配置时的正式接入点：在此调用渠道统一下单 SDK，返回真实调起参数
        DemoPayVo vo = new DemoPayVo();
        vo.setOrderNo(orderNo);
        vo.setChannel(channel);
        vo.setAmount(order.getAmount());
        vo.setPayParam("{\"demo\":true,\"orderNo\":\"" + orderNo + "\"}");
        return vo;
    }
}
