package org.dromara.system.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * OSS 文件分页结果对象（API层轻量分页，不依赖mybatis）
 *
 * @author Lion Li
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssPageDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 数据列表
     */
    private List<OssDTO> rows;

    /**
     * 总记录数
     */
    private long total;

}
