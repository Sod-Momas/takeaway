package io.github.sodmomas.takeaway.converter;

import io.github.sodmomas.takeaway.model.dto.UserAuthInfo;
import io.github.sodmomas.takeaway.model.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * @author Sod-Momas
 * @since 2023/11/4
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AccountConverter {
    @Mapping(target = "accountId", source = "id")
    UserAuthInfo toUserAuthInfo(Account account);
}
