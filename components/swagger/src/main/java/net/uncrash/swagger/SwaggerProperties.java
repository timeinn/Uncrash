package net.uncrash.swagger;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger Properties
 *
 * @author Sendya
 * @date 2019/04/04
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "uncrash.swagger2")
public class SwaggerProperties {

    /**
     * 是否开启Swagger
     */
    private boolean enabled;

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
     * API 版本
     */
    private String version;

    /**
     * TOS 地址
     */
    private String tosUrl;

    /**
     * 联系人名称
     */
    private String contactName;

    /**
     * 联系人网站
     */
    private String contactUrl;

    /**
     * 联系人邮箱
     */
    private String contactEmail;

    /**
     * 许可证
     */
    private String license;
    /**
     * 许可证网址
     */
    private String licenseUrl;

    /**
     * API 提供主机
     */
    private String host;
}
