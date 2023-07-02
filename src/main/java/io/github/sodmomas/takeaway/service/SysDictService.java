package io.github.sodmomas.takeaway.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.sodmomas.takeaway.common.model.Option;
import io.github.sodmomas.takeaway.converter.DictConverter;
import io.github.sodmomas.takeaway.mapper.SysDictMapper;
import io.github.sodmomas.takeaway.model.entity.SysDict;
import io.github.sodmomas.takeaway.model.form.DictForm;
import io.github.sodmomas.takeaway.model.query.DictPageQuery;
import io.github.sodmomas.takeaway.model.vo.DictPageVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 字典接口
 *
 * @author haoxr
 * @since 2023/3/4
 */
@Service
@RequiredArgsConstructor
public class SysDictService extends ServiceImpl<SysDictMapper, SysDict> implements IService<SysDict> {

    private final DictConverter dictConverter;

    /**
     * 字典数据项分页列表
     *
     * @param queryParams
     * @return
     */

    public Page<DictPageVO> getDictPage(DictPageQuery queryParams) {
        // 查询参数
        int pageNum = queryParams.getPageNum();
        int pageSize = queryParams.getPageSize();
        String keywords = queryParams.getKeywords();
        String typeCode = queryParams.getTypeCode();

        // 查询数据
        Page<SysDict> dictItemPage = this.page(
                new Page<>(pageNum, pageSize),
                new LambdaQueryWrapper<SysDict>()
                        .like(StrUtil.isNotBlank(keywords), SysDict::getName, keywords)
                        .eq(StrUtil.isNotBlank(typeCode), SysDict::getTypeCode, typeCode)
                        .select(SysDict::getId, SysDict::getName, SysDict::getValue, SysDict::getStatus)
        );

        // 实体转换
        Page<DictPageVO> pageResult = dictConverter.entity2Page(dictItemPage);
        return pageResult;
    }

    /**
     * 字典数据项表单详情
     *
     * @param id 字典数据项ID
     * @return
     */

    public DictForm getDictForm(Long id) {
        // 获取entity
        SysDict entity = this.getOne(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getId, id)
                .select(
                        SysDict::getId,
                        SysDict::getTypeCode,
                        SysDict::getName,
                        SysDict::getValue,
                        SysDict::getStatus,
                        SysDict::getSort,
                        SysDict::getRemark
                ));
        Assert.isTrue(entity != null, "字典数据项不存在");

        // 实体转换
        DictForm dictForm = dictConverter.entity2Form(entity);
        return dictForm;
    }

    /**
     * 新增字典数据项
     *
     * @param dictForm 字典数据项表单
     * @return
     */

    public boolean saveDict(DictForm dictForm) {
        // 实体对象转换 form->entity
        SysDict entity = dictConverter.form2Entity(dictForm);
        // 持久化
        boolean result = this.save(entity);
        return result;
    }

    /**
     * 修改字典数据项
     *
     * @param id       字典数据项ID
     * @param dictForm 字典数据项表单
     * @return
     */

    public boolean updateDict(Long id, DictForm dictForm) {
        SysDict entity = dictConverter.form2Entity(dictForm);
        boolean result = this.updateById(entity);
        return result;
    }

    /**
     * 删除字典数据项
     *
     * @param idsStr 字典数据项ID，多个以英文逗号(,)分割
     * @return
     */

    public boolean deleteDict(String idsStr) {
        Assert.isTrue(StrUtil.isNotBlank(idsStr), "删除数据为空");
        //
        List<Long> ids = Arrays.asList(idsStr.split(","))
                .stream()
                .map(id -> Long.parseLong(id))
                .collect(Collectors.toList());

        // 删除字典数据项
        boolean result = this.removeByIds(ids);
        return result;
    }

    /**
     * 获取字典下拉列表
     *
     * @param typeCode
     * @return
     */

    public List<Option> listDictOptions(String typeCode) {
        // 数据字典项
        List<SysDict> dictList = this.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getTypeCode, typeCode)
                .select(SysDict::getValue, SysDict::getName)
        );

        // 转换下拉数据
        List<Option> options = CollectionUtil.emptyIfNull(dictList)
                .stream()
                .map(dictItem -> new Option(dictItem.getValue(), dictItem.getName()))
                .collect(Collectors.toList());
        return options;
    }
}
