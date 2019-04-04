package net.uncrash.agent.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PingLog implements Serializable {

    private static final long serialVersionUID = -4748296840525032239L;

    /**
     * 节点名称
     */
    private String nodeName;

    /**
     * 重试次数
     */
    private Integer tryNo;

    /**
     * 间隔
     */
    private Double delay;

    /**
     * 延迟
     */
    private Integer timeout = 0;

}
