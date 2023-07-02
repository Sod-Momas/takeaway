package io.github.sodmomas.takeaway.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.system.model.entity.SysDictType;
import io.github.sodmomas.system.model.form.DictTypeForm;
import io.github.sodmomas.system.model.vo.DictTypePageVO;
import io.github.sodmomas.takeaway.model.entity.SysDictType;
import io.github.sodmomas.takeaway.model.form.DictTypeForm;
import io.github.sodmomas.takeaway.model.vo.DictTypePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 字典类型对象转换器
 *
 * @author haoxr
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface DictTypeConverter {
    DictTypeConverter INSTANCE = Mappers.getMapper(DictTypeConverter.class);

    DictTypePageVO map(SysDictType sysDictType);

    Page<DictTypePageVO> entity2Page(Page<SysDictType> page);

    DictTypeForm entity2Form(SysDictType entity);

    SysDictType form2Entity(DictTypeForm entity);

}
