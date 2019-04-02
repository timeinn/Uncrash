package net.uncrash.agent.domain;

import lombok.Builder;
import lombok.Data;

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
@Builder
@Data
@Entity
@Table(name = "t_server_agent_log")
public class ServerAgentLog {

    /**
     * Primary key
     */
    @Id
    private String id;

    /**
     * Agent 监控脚本版本号
     */
    private String agent;

    /**
     * 服务器连接数
     */
    private Integer connections;

    /**
     * CPU 核心数
     */
    private Integer cpuCore;

    /**
     * CPU 频率
     */
    private Double cpuFreq;

    /**
     * CPU 名称
     */
    private String cpuName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 磁盘总大小
     */
    private Long diskTotal;

    /**
     * 磁盘已用大小
     */
    private Long diskUsage;

    /**
     * 文件句柄数
     */
    private Integer fileHandles;

    /**
     * 文件句柄数最大值
     */
    private Integer fileHandlesLimit;

    private String ipv4;

    private String ipv6;

    /**
     * 系统负载 1|5|15 分钟计
     */
    private String load;

    /**
     * CPU 负载
     */
    private Double loadCpu;

    /**
     * IO 负载
     */
    private Double loadIo;

    /**
     * 网卡
     */
    private String nic;

    /**
     * 系统位
     */
    private String osArch;

    /**
     * 系统内核
     */
    private String osKernel;

    /**
     * 系统名称
     */
    private String osName;

    /**
     * 进程数
     */
    private Integer processes;

    /**
     * 内存总量
     */
    private Long ramTotal;

    /**
     * 内存使用量
     */
    private Long ramUsage;

    /**
     * 用户服务器ID
     */
    private Long serverId;

    /**
     * 会话数
     */
    private Integer sessions;

    /**
     * 交换区大小
     */
    private Long swapTotal;

    /**
     * 交换区使用量
     */
    private Long swapUsage;

    /**
     * 上传流量 (出网)
     */
    private BigInteger tx;

    private Integer txGap;

    /**
     * 下载流量 (入网)
     */
    private BigInteger rx;

    private Integer rxGap;

    /**
     * 开机时长
     */
    private Integer uptime;
}