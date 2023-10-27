package io.github.sodmomas.takeaway.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.github.sodmomas.takeaway.model.entity.DrugEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Sod-Momas
 * @since 2023/7/2
 */
@Mapper
public interface DrugMapper extends BaseMapper<DrugEntity> {
}
