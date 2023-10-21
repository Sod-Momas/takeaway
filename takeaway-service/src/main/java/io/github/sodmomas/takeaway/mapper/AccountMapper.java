package io.github.sodmomas.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.sodmomas.takeaway.model.entity.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Sod-Momas
 * @since 2023/10/21
 */
@Mapper
public interface AccountMapper extends BaseMapper<Account> {
}
