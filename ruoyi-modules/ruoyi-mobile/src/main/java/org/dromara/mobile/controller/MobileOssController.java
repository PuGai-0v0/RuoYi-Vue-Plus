package org.dromara.mobile.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import jakarta.validation.constraints.NotEmpty;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.web.core.BaseController;
import org.dromara.system.api.OssService;
import org.dromara.system.api.domain.OssPageDTO;
import org.dromara.system.api.domain.OssUploadDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 移动端-文件上传/下载（复用框架 S3 协议 OSS 存储，存储后端由 sys_oss_config 决定）
 */
@SaCheckLogin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/oss")
public class MobileOssController extends BaseController {

    private final OssService ossService;

    /**
     * 上传文件到OSS
     *
     * @param file 文件
     * @return 上传结果
     */
    @Log(title = "移动端文件上传", businessType = BusinessType.INSERT)
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public R<OssUploadDTO> upload(@RequestPart("file") MultipartFile file) {
        return R.ok(ossService.upload(file));
    }

    /**
     * 按当前登录用户分页查询已上传文件
     *
     * @param pageQuery  分页参数
     * @return 文件列表
     */
    @GetMapping("/list")
    public R<OssPageDTO> list(PageQuery pageQuery) {
        return R.ok(ossService.pageByUser(pageQuery.getPageNum(), pageQuery.getPageSize()));
    }

    /**
     * 文件下载方法，支持一次性下载完整文件
     *
     * @param ossId OSS对象ID
     * @return 文件流
     */
    @GetMapping("/download/{ossId}")
    public ResponseEntity<byte[]> download(@PathVariable Long ossId) {
        return ossService.download(ossId);
    }

    /**
     * 删除文件
     *
     * @param ossIds 文件ID数组
     * @return 操作结果
     */
    @Log(title = "移动端文件删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ossIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空") @PathVariable Long[] ossIds) {
        return toAjax(ossService.deleteByIds(ossIds));
    }
}
