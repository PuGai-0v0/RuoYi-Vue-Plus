package org.dromara.mobile.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 移动端演示条目对象 mobile_demo_item
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("mobile_demo_item")
public class DemoItem extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @TableId(value = "id")
    private Long id;

    /** 标题 */
    private String title;

    /** 分类 */
    private String category;

    /** 状态（0正常 1停用） */
    private String status;

    /** 金额 */
    private BigDecimal amount;

    /** 计划日期 */
    private Date planDate;

    /** 是否加急（0否 1是） */
    private String urgent;

    /** 标签（逗号分隔） */
    private String tags;

    /** 备注 */
    private String remark;
}
