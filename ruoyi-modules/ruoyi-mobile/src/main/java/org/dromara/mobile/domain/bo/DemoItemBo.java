package org.dromara.mobile.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.mobile.domain.DemoItem;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 移动端演示条目业务对象
 */
@Data
@AutoMapper(target = DemoItem.class, reverseConvertGenerate = false)
public class DemoItemBo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 主键 */
    @NotNull(message = "主键不能为空", groups = {EditGroup.class})
    private Long id;

    /** 标题 */
    @NotBlank(message = "标题不能为空", groups = {AddGroup.class, EditGroup.class})
    private String title;

    /** 分类 */
    private String category;

    /** 状态（0正常 1停用） */
    private String status;

    /** 金额 */
    private BigDecimal amount;

    /** 计划日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date planDate;

    /** 是否加急（0否 1是） */
    private String urgent;

    /** 标签（逗号分隔） */
    private String tags;

    /** 备注 */
    private String remark;
}
