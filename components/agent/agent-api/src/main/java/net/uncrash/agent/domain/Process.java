package net.uncrash.agent.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Sendya
 */
@Data
@Builder
public class Process implements Serializable {

    private static final long serialVersionUID = 421484596886747571L;

    /**
     * 进程名称
     */
    private String name;

    /**
     * 占用 cpu
     */
    private BigDecimal cpu;

    /**
     * 占用内存
     */
    private BigDecimal memory;

    /**
     * 运行用户
     */
    private String user;

}
