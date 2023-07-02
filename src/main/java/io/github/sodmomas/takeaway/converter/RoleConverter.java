package io.github.sodmomas.takeaway.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.common.model.Option;
import io.github.sodmomas.takeaway.model.entity.SysRole;
import io.github.sodmomas.takeaway.model.form.RoleForm;
import io.github.sodmomas.takeaway.model.vo.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @author haoxr
 * @since 2022/5/29
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> entity2Page(Page<SysRole> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(SysRole role);


    List<Option> entities2Options(List<SysRole> roles);

    SysRole form2Entity(RoleForm roleForm);

    RoleForm entity2Form(SysRole entity);
}