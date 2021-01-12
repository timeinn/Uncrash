package net.uncrash.agent.domain.consts;

import lombok.Getter;

import java.util.Arrays;

public enum PingNode {
    NODE_UNK(-1, "unknown"),
    NODE_CN(1, "cn"),
    NODE_HK(2, "hk"),
    NODE_JP(3, "jp"),
    NODE_EU(4, "eu"),
    NODE_SG(5, "sg"),
    NODE_US(6, "us"),
    ;

    @Getter
    private byte index;
    @Getter
    private String name;

    PingNode(int index, String name) {
        this.index = (byte) index;
        this.name = name;
    }

    public static PingNode of(int index) {
        return Arrays
            .stream(values())
            .filter(node -> node.index == index)
            .findAny()
            .orElse(PingNode.NODE_UNK);
    }
}
