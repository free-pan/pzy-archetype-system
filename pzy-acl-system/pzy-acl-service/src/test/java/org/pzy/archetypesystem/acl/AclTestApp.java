package org.pzy.archetypesystem.acl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

/**
 * @author pan
 * @date 2020/3/25
 */
@SpringBootApplication
@MapperScan(value = "org.pzy.archetypesystem.acl.*.dao", annotationClass = Repository.class)
public class AclTestApp {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(AclTestApp.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }
}
