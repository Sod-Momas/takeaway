package io.github.sodmomas.takeaway.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.sodmomas.takeaway.model.bo.UserBO;
import io.github.sodmomas.takeaway.model.bo.UserFormBO;
import io.github.sodmomas.takeaway.model.entity.SysUser;
import io.github.sodmomas.takeaway.model.form.UserForm;
import io.github.sodmomas.takeaway.model.vo.UserImportVO;
import io.github.sodmomas.takeaway.model.vo.UserInfoVO;
import io.github.sodmomas.takeaway.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author haoxr
 * @since 2022/6/8
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(io.github.sodmomas.takeaway.common.base.IBaseEnum.getLabelByValue(bo.getGender(), io.github.sodmomas.takeaway.common.enums.GenderEnum.class))")
    })
    UserPageVO bo2Vo(UserBO bo);

    Page<UserPageVO> bo2Vo(Page<UserBO> bo);

    UserForm bo2Form(UserFormBO bo);

    UserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id"),
    })
    UserInfoVO entity2UserInfoVo(SysUser entity);

    SysUser importVo2Entity(UserImportVO vo);

}
