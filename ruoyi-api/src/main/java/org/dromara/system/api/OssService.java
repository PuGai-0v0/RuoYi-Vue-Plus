package org.dromara.system.api;

import org.dromara.system.api.domain.OssDTO;
import org.dromara.system.api.domain.OssPageDTO;
import org.dromara.system.api.domain.OssUploadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 通用 OSS服务
 *
 * @author Lion Li
 */
public interface OssService {

    /**
     * 通过ossId查询对应的url
     *
     * @param ossIds ossId串逗号分隔
     * @return url串逗号分隔
     */
    String selectUrlByIds(String ossIds);

    /**
     * 通过ossId查询列表
     *
     * @param ossIds ossId串逗号分隔
     * @return 列表
     */
    List<OssDTO> selectByIds(String ossIds);

    /**
     * 上传文件到OSS
     *
     * @param file 文件
     * @return 上传结果
     */
    OssUploadDTO upload(MultipartFile file);

    /**
     * 文件下载方法，支持一次性下载完整文件
     *
     * @param ossId OSS对象ID
     */
    ResponseEntity<byte[]> download(Long ossId);

    /**
     * 删除文件
     *
     * @param ossIds 文件ID数组
     * @return 是否成功
     */
    Boolean deleteByIds(Long[] ossIds);

    /**
     * 按当前登录用户分页查询已上传文件
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return 文件列表
     */
    OssPageDTO pageByUser(int pageNum, int pageSize);
}
