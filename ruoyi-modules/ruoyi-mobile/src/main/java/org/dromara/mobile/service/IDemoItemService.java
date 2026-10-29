package org.dromara.mobile.service;

import org.dromara.common.core.domain.PageResult;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.mobile.domain.bo.DemoItemBo;
import org.dromara.mobile.domain.vo.DemoItemVo;

/**
 * 移动端演示条目 Service 接口
 */
public interface IDemoItemService {

    /**
     * 查询移动端演示条目列表
     *
     * @param bo 移动端演示条目
     * @param pageQuery 分页参数
     * @return 移动端演示条目集合
     */
    PageResult<DemoItemVo> queryPageList(DemoItemBo bo, PageQuery pageQuery);

    /**
     * 查询移动端演示条目
     *
     * @param id 主键
     * @return 移动端演示条目
     */
    DemoItemVo queryById(Long id);

    /**
     * 新增移动端演示条目
     *
     * @param bo 移动端演示条目
     * @return 是否新增成功
     */
    Boolean insertByBo(DemoItemBo bo);

    /**
     * 修改移动端演示条目
     *
     * @param bo 移动端演示条目
     * @return 是否修改成功
     */
    Boolean updateByBo(DemoItemBo bo);

    /**
     * 删除移动端演示条目
     *
     * @param ids 主键
     * @return 是否删除成功
     */
    Boolean deleteByIds(Long[] ids);
}
