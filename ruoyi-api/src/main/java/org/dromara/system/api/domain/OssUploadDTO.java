package org.dromara.system.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * OSS 文件上传结果对象
 *
 * @author Lion Li
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OssUploadDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * URL地址
     */
    private String url;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 对象存储主键
     */
    private String ossId;

}
