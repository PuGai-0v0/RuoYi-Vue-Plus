-- ----------------------------
-- 移动端演示表（RuoYi-Vue-Plus 6.x，无租户）
-- 执行库：与 ry-vue 主库相同
-- ----------------------------

DROP TABLE IF EXISTS mobile_demo_item;
CREATE TABLE mobile_demo_item (
    id          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键',
    title       VARCHAR(100) NOT NULL                COMMENT '标题',
    category    VARCHAR(20)  DEFAULT '办公'          COMMENT '分类',
    status      CHAR(1)      DEFAULT '0'             COMMENT '状态（0正常 1停用）',
    amount      DECIMAL(10,2) DEFAULT 0.00           COMMENT '金额',
    plan_date   DATE         DEFAULT NULL            COMMENT '计划日期',
    urgent      CHAR(1)      DEFAULT '0'             COMMENT '是否加急（0否 1是）',
    tags        VARCHAR(100) DEFAULT NULL            COMMENT '标签（逗号分隔）',
    remark      VARCHAR(500) DEFAULT NULL            COMMENT '备注',
    create_dept BIGINT       DEFAULT NULL            COMMENT '创建部门',
    create_by   BIGINT       DEFAULT NULL            COMMENT '创建者',
    create_time DATETIME     DEFAULT NULL            COMMENT '创建时间',
    update_by   BIGINT       DEFAULT NULL            COMMENT '更新者',
    update_time DATETIME     DEFAULT NULL            COMMENT '更新时间',
    PRIMARY KEY (id)
) ENGINE=InnoDB COMMENT='移动端演示条目表';

INSERT INTO mobile_demo_item (title, category, status, amount, remark, create_dept, create_by, create_time) VALUES
('年度采购合同审批', '办公', '0', 12800.00, '演示数据-办公类', 103, 1, NOW()),
('差旅报销单', '财务', '0', 860.50, '演示数据-财务类', 103, 1, NOW()),
('新员工入职登记', '人事', '0', 0.00, '演示数据-人事类', 103, 1, NOW()),
('会议室预约', '行政', '0', 0.00, '演示数据-行政类', 103, 1, NOW()),
('办公用品申领', '行政', '0', 320.00, '演示数据-行政类', 103, 1, NOW()),
('季度预算申报', '财务', '0', 56000.00, '演示数据-财务类', 103, 1, NOW()),
('项目立项申请', '办公', '0', 0.00, '演示数据-办公类', 103, 1, NOW()),
('加班餐补申请', '财务', '1', 150.00, '演示数据-停用示例', 103, 1, NOW()),
('培训报名', '人事', '0', 0.00, '演示数据-人事类', 103, 1, NOW()),
('固定资产盘点', '行政', '0', 0.00, '演示数据-行政类', 103, 1, NOW()),
('用车申请', '行政', '0', 0.00, '演示数据-行政类', 103, 1, NOW()),
('发票开具申请', '财务', '0', 3200.00, '演示数据-财务类', 103, 1, NOW()),
('用印申请', '办公', '0', 0.00, '演示数据-办公类', 103, 1, NOW()),
('招聘需求提报', '人事', '0', 0.00, '演示数据-人事类', 103, 1, NOW()),
('系统权限申请', '办公', '0', 0.00, '演示数据-办公类', 103, 1, NOW()),
('费用分摊确认', '财务', '0', 990.00, '演示数据-财务类', 103, 1, NOW()),
('访客登记', '行政', '0', 0.00, '演示数据-行政类', 103, 1, NOW()),
('绩效申诉', '人事', '1', 0.00, '演示数据-停用示例', 103, 1, NOW()),
('数据导出申请', '办公', '0', 0.00, '演示数据-办公类', 103, 1, NOW()),
('办公用品采购', '行政', '0', 1200.00, '演示数据-行政类', 103, 1, NOW());

DROP TABLE IF EXISTS mobile_demo_order;
CREATE TABLE mobile_demo_order (
    order_id    BIGINT        NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    order_no    VARCHAR(32)   NOT NULL                COMMENT '订单号',
    subject     VARCHAR(100)  NOT NULL                COMMENT '商品主题',
    amount      DECIMAL(10,2) NOT NULL                COMMENT '订单金额',
    channel     VARCHAR(20)   DEFAULT NULL            COMMENT '支付渠道（wechat微信 alipay支付宝）',
    status      VARCHAR(20)   DEFAULT 'pending'       COMMENT '订单状态（pending待支付 paid已支付 cancelled已取消）',
    pay_time    DATETIME      DEFAULT NULL            COMMENT '支付时间',
    create_dept BIGINT        DEFAULT NULL            COMMENT '创建部门',
    create_by   BIGINT        DEFAULT NULL            COMMENT '创建者',
    create_time DATETIME      DEFAULT NULL            COMMENT '创建时间',
    update_by   BIGINT        DEFAULT NULL            COMMENT '更新者',
    update_time DATETIME      DEFAULT NULL            COMMENT '更新时间',
    PRIMARY KEY (order_id),
    UNIQUE KEY uk_order_no (order_no)
) ENGINE=InnoDB COMMENT='移动端演示订单表';
