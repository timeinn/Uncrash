package net.uncrash.server.consts;

import javax.persistence.AttributeConverter;

/**
 * 监控类型 枚举
 * @author Sendya
 */
public enum MonitorType {

    /**
     * 脚本监控
     */
    AGENT(0),
    /**
     * HTTP(s) 监控
     */
    CURL(1),
    /**
     * ICMP 协议监控
     */
    ICMP(2),
    /**
     * 端口 telnet / ping 监控
     */
    PORT(3);

    private int type;

    MonitorType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public static MonitorType fromValue(int value) {
        for (MonitorType monitorType : MonitorType.values()) {
            if (monitorType.type == value) {
                return monitorType;
            }
        }
        return AGENT;
    }

    public static class MonitorTypeConvert implements AttributeConverter<MonitorType, Integer> {

        @Override
        public Integer convertToDatabaseColumn(MonitorType monitorType) {
            return monitorType.type;
        }

        @Override
        public MonitorType convertToEntityAttribute(Integer integer) {
            return MonitorType.fromValue(integer);
        }
    }
}
