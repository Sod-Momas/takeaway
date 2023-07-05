package io.github.sodmomas.takeaway.drug;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Sod-Momas
 * @since 2023/7/5
 */
@Service
public class DrugService extends ServiceImpl<DrugMapper, DrugEntity> implements IService<DrugEntity> {
}
