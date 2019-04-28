package net.uncrash.server.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 监控对象表
 * 用于储存要监控的对象
 * 对象可以是服务器、一个 HTTP(s) 网址、一个 IP 或域名
 *
 * @author Sendya
 *
 */
@ApiModel
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
@Table(name = "t_user_monitor")
public class UserMonitor implements Serializable {

    /**
     * Primary key
     */
    @Id
    @ApiModelProperty("主键")
    @Column(length = 32)
    private String id;

    /**
     * 唯一
     */
    @ApiModelProperty("该监控归属的用户 ID")
    @Column(columnDefinition = "varchar(32) comment '该监控归属的用户 ID'")
    private String userId;

    /**
     * 监控名称
     * 最好是服务器的主机名或 FQDN
     */
    @ApiModelProperty("监控名称 FQDN")
    @Column(columnDefinition = "varchar(100) comment '监控名称'")
    private String monitorName;

    /**
     * 监控主机
     * 可以是域名、IP(可带端口)、一个可访问的地址
     */
    @ApiModelProperty("监控主机 可以是域名、IP(可带端口)、一个可访问的地址")
    @Column(columnDefinition = "varchar(100) comment '监控主机 可以是域名、IP(可带端口)、一个可访问的地址'")
    private String monitorHost;

    /**
     * Agent 模式下推送 Token
     */
    @ApiModelProperty("Agent 模式下：推送 Token")
    @Column(columnDefinition = "varchar(64) comment 'Agent 模式下：推送 Token'")
    private String pushToken;

    /**
     * 数据丢失通知
     *
     * 0 未开启、1 及以上为超过次数后进行通知
     */
    @ApiModelProperty("数据丢失通知：0 未开启、1 及以上为超过次数后进行通知")
    @Column(columnDefinition = "int comment '数据丢失通知：0 未开启、1 及以上为超过次数后进行通知'")
    private Integer lossNotification;

    /**
     * 数据丢失时额外 PING
     * 0 关、1 开
     */
    @ApiModelProperty("数据丢失时，进行额外的 PING 检查 0 关、1 开")
    @Column(nullable = false, columnDefinition = "TINYINT(1) comment '数据丢失时，进行额外的 PING 检查 0 关、1 开'", length = 1)
    private Boolean lossRequirePing;

    /**
     * 资源使用阈值通知
     * 0 关、1 开
     */
    @ApiModelProperty("资源使用阈值通知 0 关、1 开")
    @Column(nullable = false, columnDefinition = "TINYINT(1) comment '资源使用阈值通知 0 关、1 开'", length = 1)
    private Boolean resourceNotification;

    /**
     * 系统负载阈值(单位百分比)
     * 0 - 100
     */
    @ApiModelProperty("系统负载阈值：0 - 100 单位百分比")
    @Column(columnDefinition = "int comment '系统负载阈值：0 - 100 单位百分比'")
    private Integer systemLoad;

    /**
     * 内存使用量阈值(单位百分比)
     * 0 - 100
     */
    @ApiModelProperty("内存使用量阈值：0 - 100 单位百分比")
    @Column(columnDefinition = "int comment '内存使用量阈值：0 - 100 单位百分比'")
    private Integer ramUsage;

    /**
     * 硬盘使用量阈值(单位百分比)
     * 0 - 100
     */
    @ApiModelProperty("硬盘使用量阈值：0 - 100 单位百分比")
    @Column(columnDefinition = "int comment '硬盘使用量阈值：0 - 100 单位百分比'")
    private Integer diskUsage;

    /**
     * 监控状态
     * 0 停用 1 启用 2 已离线
     */
    @ApiModelProperty("监控状态：0 停用 1 启用 2 已离线")
    @Column(columnDefinition = "tinyint(1) comment '监控状态：0 停用 1 启用 2 已离线'")
    private Integer status;

    /**
     * 监控类型
     * 0 Agent 推送监控
     * 1 cURL HTTP(s) 请求监控
     * 2 ICMP (Ping) 监控
     * 3 Port 端口监控
     */
    @ApiModelProperty("监控类型：0 Agent 推送、1 cURL HTTP(s) 请求、2 ICMP (Ping)、3 Port 端口")
    @Column(columnDefinition = "tinyint(1) comment '监控类型：0 Agent 推送、1 cURL HTTP(s) 请求、2 ICMP (Ping)、3 Port 端口'")
    private Integer type;

    @Column
    private Date createdTime;

    @Column
    private Date updatedTime;
}
