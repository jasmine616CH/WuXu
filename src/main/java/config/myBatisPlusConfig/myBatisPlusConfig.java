package config.myBatisPlusConfig;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *My-Batis插件拦截器
 * 实现自动分页、乐观锁等功能
 */
@Configuration
@MapperScan({
        "config.easyExcel.read.mapper",
        "module.user.mapper",
        "module.goods.mapper",
        "module.useLog.mapper"
})
public class myBatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));

        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        return interceptor;

    }

}
