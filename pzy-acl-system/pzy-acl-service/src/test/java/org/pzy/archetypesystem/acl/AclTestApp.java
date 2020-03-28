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
 * @author pan
 * @date 2020/3/25
 */
@SpringBootApplication
@EnableAsync
@EnableCaching(order = GlobalConstant.AOP_ORDER_CACHE)
@EnableTransactionManagement(order = GlobalConstant.AOP_ORDER_TRANSACTIONAL)
@EnableConfigurationProperties
@MapperScan(value = "org.pzy.archetypesystem.acl.*.dao", annotationClass = Repository.class)
public class AclTestApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AclTestApp.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
