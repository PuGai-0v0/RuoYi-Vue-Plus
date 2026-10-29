package org.dromara.mobile.service;

import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.mobile.domain.DemoOrder;
import org.dromara.mobile.domain.bo.DemoOrderBo;
import org.dromara.mobile.domain.vo.DemoOrderVo;
import org.dromara.mobile.domain.vo.DemoPayVo;

/**
 * 移动端演示订单 Service 接口
 */
public interface IDemoOrderService {

    /**
     * 创建移动端演示订单
     *
     * @param bo 移动端演示订单
     * @return 移动端演示订单
     */
    DemoOrderVo createOrder(DemoOrderBo bo);

    /**
     * 查询移动端演示订单分页列表
     *
     * @param pageQuery 分页参数
     * @return 移动端演示订单集合
     */
    PageResult<DemoOrderVo> queryPageList(PageQuery pageQuery);

    /**
     * 查询移动端演示订单
     *
     * @param orderNo 订单号
     * @return 移动端演示订单
     */
    DemoOrder getByOrderNo(String orderNo);

    /**
     * 订单支付
     *
     * @param orderNo 订单号
     * @param channel 支付渠道
     * @return 支付信息
     */
    DemoPayVo prepay(String orderNo, String channel);
}
