package net.uncrash.agent.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * Server Agent Log Domain
 *
 * @author Sendya
 * @date 2019/01/13
 */
@ApiModel
@Builder
@Data
@Entity
@Table(name = "t_server_agent_log")
public class ServerAgentLog {

    /**
     * Primary key
     */
    @Id
    @ApiModelProperty("主键")
    private String id;

    /**
     * Agent 监控脚本版本号
     */
    @ApiModelProperty("Agent 监控脚本版本号")
    private String agent;

    /**
     * 服务器连接数
     */
    @ApiModelProperty("服务器连接数")
    private Integer connections;

    /**
     * CPU 核心数
     */
    @ApiModelProperty("CPU 核心数")
    @Column(precision = 8)
    @Type(type = "big_decimal")
    private Integer cpuCore;

    /**
     * CPU 频率
     */
    @ApiModelProperty("CPU 频率")
    @Column(precision = 8, scale = 2)
    @Type(type = "big_decimal")
    private Double cpuFreq;

    /**
     * CPU 名称
     */
    @ApiModelProperty("CPU 名称")
    private String cpuName;

    /**
     * 磁盘总大小
     */
    @ApiModelProperty("磁盘总大小")
    private Long diskTotal;

    /**
     * 磁盘已用大小
     */
    @ApiModelProperty("磁盘已用大小")
    private Long diskUsage;

    /**
     * 文件句柄数
     */
    @ApiModelProperty("文件句柄数")
    private Integer fileHandles;

    /**
     * 文件句柄数最大值
     */
    @ApiModelProperty("文件句柄数最大值")
    private Integer fileHandlesLimit;

    @ApiModelProperty("IPv4")
    private String ipv4;

    @ApiModelProperty("IPv6")
    private String ipv6;

    /**
     * 系统负载 1|5|15 分钟计
     */
    @ApiModelProperty("系统负载 1|5|15 分钟计")
    private String load;

    /**
     * CPU 负载
     */
    @ApiModelProperty("CPU 负载")
    @Column(precision = 8, scale = 2)
    @Type(type = "big_decimal")
    private Double loadCpu;

    /**
     * IO 负载
     */
    @ApiModelProperty("IO 负载")
    @Column(precision = 8, scale = 2)
    @Type(type = "big_decimal")
    private Double loadIo;

    /**
     * 网卡
     */
    @ApiModelProperty("网卡")
    private String nic;

    /**
     * 系统位数
     */
    @ApiModelProperty("系统位数")
    private String osArch;

    /**
     * 系统内核
     */
    @ApiModelProperty("系统内核")
    private String osKernel;

    /**
     * 系统名称
     */
    @ApiModelProperty("系统名称")
    private String osName;

    /**
     * 进程数
     */
    @ApiModelProperty("进程数")
    private Integer processes;

    /**
     * 内存总量
     */
    @ApiModelProperty("内存总量")
    private Long ramTotal;

    /**
     * 内存使用量
     */
    @ApiModelProperty("内存使用量")
    private Long ramUsage;

    /**
     * 用户服务器ID
     */
    @ApiModelProperty("用户服务器ID")
    private Long serverId;

    /**
     * 会话数
     */
    @ApiModelProperty("会话数")
    private Integer sessions;

    /**
     * Swap分区大小
     */
    @ApiModelProperty("Swap分区大小")
    private Long swapTotal;

    /**
     * Swap分区使用量
     */
    @ApiModelProperty("Swap分区使用量")
    private Long swapUsage;

    /**
     * 上传流量 (出网)
     */
    @ApiModelProperty("出网流量")
    private BigInteger tx;

    @ApiModelProperty("出网流量Gap")
    private Integer txGap;

    /**
     * 下载流量 (入网)
     */
    @ApiModelProperty("入网流量")
    private BigInteger rx;

    @ApiModelProperty("入网流量Gap")
    private Integer rxGap;

    /**
     * 开机时长
     */
    @ApiModelProperty("开机时长")
    private Integer uptime;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;
}