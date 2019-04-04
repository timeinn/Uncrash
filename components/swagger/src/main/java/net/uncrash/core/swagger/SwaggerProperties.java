package net.uncrash.core.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.Tag;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix="spring.boot.swagger2")
public class SwaggerProperties {

    /**
     * 是否开启
     */
    private boolean allow;

    /**
     * API Controller 扫描包路径
     */
    private String basePackage;

    /**
     * 文档名称
     */
    private String title;

    /**
     * 说明
     */
    private String description;

    /**
     * 服务地址
     */
    private String serviceUrl;

    /**
     * 联系方式
     */
    private String contact;
    /**
     * API 版本
     */
    private String version;

    /**
     * API 提供主机
     */
    private String host;

    /**
     * 文档标签数组
     */
    private List<Tag> tags;

}
