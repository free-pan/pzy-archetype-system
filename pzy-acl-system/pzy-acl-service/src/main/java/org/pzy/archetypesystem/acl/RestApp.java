package org.pzy.archetypesystem.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.pzy.opensource.domain.GlobalConstant;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 服务启动类
 *
 * @author pan
 * @since 2020-04-03
 */
@SpringBootApplication
@EnableAsync
@EnableCaching(order = GlobalConstant.AOP_ORDER_CACHE)
@EnableTransactionManagement(order = GlobalConstant.AOP_ORDER_TRANSACTIONAL)
@EnableConfigurationProperties
@MapperScan(value = "org.pzy.archetypesystem.acl.**.dao", annotationClass = Repository.class)
public class RestApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AclTestApp.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
