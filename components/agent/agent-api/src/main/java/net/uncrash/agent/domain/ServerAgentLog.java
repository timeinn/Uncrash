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
     * Agent 监控脚本号
     */
    private String agent;

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

    private LocalDateTime createTime;

    private Long diskTotal;

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

    private Long ramTotal;

    private Long ramUsage;

    private BigInteger rx;

    private Integer rxGap;

    /**
     * 用户服务器ID
     */
    private Long serverId;

    /**
     * 会话数
     */
    private Integer sessions;

    private Long swapTotal;

    private Long swapUsage;

    private BigInteger tx;

    private Integer txGap;

    /**
     * 开机时长
     */
    private Integer uptime;
}