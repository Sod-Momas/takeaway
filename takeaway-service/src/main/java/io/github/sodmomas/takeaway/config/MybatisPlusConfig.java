package io.github.sodmomas.takeaway.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.incrementer.PostgreKeyGenerator;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis-plus 配置类
 *
 * @author haoxr
 * @since 2022/7/2
 */
@Configuration(proxyBeanMethods = false)
@EnableTransactionManagement
public class MybatisPlusConfig {

    /**
     * 分页插件和数据权限插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        //分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.POSTGRE_SQL));
        return interceptor;
    }

    /**
     * 自动填充数据库创建人、创建时间、更新人、更新时间
     */
    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setMetaObjectHandler(new MyBatisPlusMetaObjectHandler());
        return globalConfig;
    }

    /**
     * <a href="https://blog.csdn.net/guashengzan0281/article/details/104312290> mybatis-plus3+postgresql insert 自增序列配置</a>
     */
    @Bean
    PostgreKeyGenerator postgreKeyGenerator() {
        return new PostgreKeyGenerator();
    }

}
