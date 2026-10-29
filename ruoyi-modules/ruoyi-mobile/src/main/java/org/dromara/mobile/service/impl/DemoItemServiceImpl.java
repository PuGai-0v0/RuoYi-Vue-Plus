package org.dromara.mobile.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.PageResult;
import org.dromara.mobile.domain.DemoItem;
import org.dromara.mobile.domain.bo.DemoItemBo;
import org.dromara.mobile.domain.vo.DemoItemVo;
import org.dromara.mobile.mapper.DemoItemMapper;
import org.dromara.mobile.service.IDemoItemService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 移动端演示条目 Service 业务层处理
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class DemoItemServiceImpl implements IDemoItemService {

    private final DemoItemMapper baseMapper;

    /**
     * 查询移动端演示条目列表
     *
     * @param bo 移动端演示条目
     * @param pageQuery 分页参数
     * @return 移动端演示条目集合
     */
    @Override
    public PageResult<DemoItemVo> queryPageList(DemoItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<DemoItem> lqw = Wrappers.lambdaQuery(DemoItem.class)
            .like(StringUtils.isNotBlank(bo.getTitle()), DemoItem::getTitle, bo.getTitle())
            .eq(StringUtils.isNotBlank(bo.getCategory()), DemoItem::getCategory, bo.getCategory())
            .eq(StringUtils.isNotBlank(bo.getStatus()), DemoItem::getStatus, bo.getStatus())
            .orderByDesc(DemoItem::getCreateTime);
        IPage<DemoItemVo> page = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return PageResult.build(page.getRecords(), page.getTotal());
    }

    /**
     * 查询移动端演示条目
     *
     * @param id 主键
     * @return 移动端演示条目
     */
    @Override
    public DemoItemVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 新增移动端演示条目
     *
     * @param bo 移动端演示条目
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(DemoItemBo bo) {
        DemoItem entity = MapstructUtils.convert(bo, DemoItem.class);
        boolean flag = baseMapper.insert(entity) > 0;
        bo.setId(entity.getId());
        return flag;
    }

    /**
     * 修改移动端演示条目
     *
     * @param bo 移动端演示条目
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(DemoItemBo bo) {
        DemoItem entity = MapstructUtils.convert(bo, DemoItem.class);
        return baseMapper.updateById(entity) > 0;
    }

    /**
     * 删除移动端演示条目
     *
     * @param ids 主键
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteByIds(Long[] ids) {
        return baseMapper.deleteByIds(List.of(ids)) > 0;
    }
}
