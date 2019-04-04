package net.uncrash.agent.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Sendya
 */
@Data
@Builder
public class DiskLog implements Serializable {

    private static final long serialVersionUID = -4500131362681941623L;

    /**
     * 驱动器号 / 地址
     */
    private String device;

    /**
     * 磁盘总大小
     */
    private Long totalSize;

    /**
     * 磁盘已用大小
     */
    private Long usedSize;

}
