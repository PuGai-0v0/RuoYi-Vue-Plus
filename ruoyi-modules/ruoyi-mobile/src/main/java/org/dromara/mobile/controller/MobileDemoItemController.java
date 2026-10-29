package org.dromara.mobile.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.PageResult;
import org.dromara.common.redis.annotation.RepeatSubmit;
import org.dromara.common.web.core.BaseController;
import org.dromara.mobile.domain.bo.DemoItemBo;
import org.dromara.mobile.domain.vo.DemoItemVo;
import org.dromara.mobile.service.IDemoItemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 移动端-演示条目（app 客户端仅可访问 /app/** 路径）
 */
@SaCheckLogin
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/demo/item")
public class MobileDemoItemController extends BaseController {

    private final IDemoItemService demoItemService;

    /**
     * 查询移动端演示条目列表
     *
     * @param bo 移动端演示条目
     * @param pageQuery 分页参数
     * @return 移动端演示条目集合
     */
    @GetMapping("/list")
    public R<PageResult<DemoItemVo>> list(DemoItemBo bo, PageQuery pageQuery) {
        return R.ok(demoItemService.queryPageList(bo, pageQuery));
    }

    /**
     * 查询移动端演示条目
     *
     * @param id 主键
     * @return 移动端演示条目
     */
    @GetMapping("/{id}")
    public R<DemoItemVo> getInfo(@PathVariable Long id) {
        return R.ok(demoItemService.queryById(id));
    }

    /**
     * 新增移动端演示条目
     *
     * @param bo 移动端演示条目
     * @return 操作结果
     */
    @Log(title = "移动端演示条目", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody DemoItemBo bo) {
        return toAjax(demoItemService.insertByBo(bo));
    }

    /**
     * 修改移动端演示条目
     *
     * @param bo 移动端演示条目
     * @return 操作结果
     */
    @Log(title = "移动端演示条目", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody DemoItemBo bo) {
        return toAjax(demoItemService.updateByBo(bo));
    }

    /**
     * 删除移动端演示条目
     *
     * @param ids 主键
     * @return 操作结果
     */
    @Log(title = "移动端演示条目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@PathVariable Long[] ids) {
        return toAjax(demoItemService.deleteByIds(ids));
    }
}
