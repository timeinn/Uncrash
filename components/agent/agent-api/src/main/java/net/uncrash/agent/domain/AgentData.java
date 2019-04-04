package net.uncrash.agent.domain;

import sun.management.Agent;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class AgentData {

    private String token;

    private String data;

    public Optional<ServerAgentLog> builder() {
        if (this.data == null || this.data.length() <= 0) {
            return Optional.empty();
        }

        List<String> data = explode(this.data, " ");

        List<Process> processList = AgentData.decodeProcessList(data.get(4));
        List<DiskLog> diskList = AgentData.decodeDiskList(data.get(17));
        List<Double> systemLoadList = AgentData.decodeSystemLoad(data.get(28));
        List<PingLog> pingDataList = AgentData.decodePingList(data.get(31));


        ServerAgentLog agentLog = ServerAgentLog.builder()
            .agent(data.get(0))
            .uptime(Integer.valueOf(data.get(1)))
            .sessions(Integer.valueOf(data.get(2)))
            .processes(Integer.valueOf(data.get(3)))
            .fileHandles(Integer.valueOf(data.get(5)))
            .fileHandlesLimit(Integer.valueOf(data.get(6)))
            .osKernel(data.get(7))
            .osName(data.get(8))
            .osArch(data.get(9))
            .cpuName(data.get(10))
            .cpuCore(Integer.valueOf(data.get(11)))
            .cpuFreq(Double.valueOf(data.get(12)))
            .ramTotal(Long.valueOf(data.get(13)))
            .ramUsage(Long.valueOf(data.get(14)))
            .swapTotal(Long.valueOf(data.get(15)))
            .swapUsage(Long.valueOf(data.get(16)))
            .diskTotal(Long.valueOf(data.get(18)))
            .diskUsage(Long.valueOf(data.get(19)))
            .connections(Integer.valueOf(data.get(20)))
            .nic(data.get(21))
            .ipv4(data.get(22))
            .ipv6(data.get(23))
            .rx(new BigInteger(data.get(24)))
            .tx(new BigInteger(data.get(25)))
            .rxGap(Integer.valueOf(data.get(26)))
            .txGap(Integer.valueOf(data.get(27)))
            .load(data.get(28))
            .loadCpu(Double.valueOf(data.get(29)))
            .loadIo(Double.valueOf(data.get(30)))
            .build();
        return Optional.of(agentLog);
    }

    public static List<Process> decodeProcessList(String json) {
        if (json == null || json.length() == 0) {
            return null;
        }
        List<String> data = Arrays.asList(json.split(";"));
        return data.stream()
            .map(AgentData::decodeProcess)
            .collect(Collectors.toList());
    }

    private static Process decodeProcess(String str) {
        List<String> data = Arrays.asList(str.trim().split(" "));
        return Process.builder()
            .name(data.get(3))
            .cpu(new BigDecimal(data.get(1)))
            .memory(new BigDecimal(data.get(2)))
            .user(data.get(0))
            .build();
    }

    public static String encodeProcess(Process process) {
        return "";
    }

    public static List<DiskLog> decodeDiskList(String json) {
        if (json == null || json.length() == 0) {
            return null;
        }
        return Arrays.asList(json.trim().split(";")).stream()
            .map(AgentData::decodeDisk)
            .collect(Collectors.toList());
    }

    private static DiskLog decodeDisk(String str) {
        List<String> data = Arrays.asList(str.trim().split(" "));
        return DiskLog.builder()
            .device(data.get(0))
            .totalSize(Long.valueOf(data.get(1)))
            .usedSize(Long.valueOf(data.get(2)))
            .build();
    }

    public static List<Double> decodeSystemLoad(String json) {
        return Arrays.asList(json.trim().split(" ")).stream()
            .filter(s -> s != null && s.length() > 0)
            .map(s -> Double.valueOf(s))
            .collect(Collectors.toList());
    }

    public static List<PingLog> decodePingList(String json) {
        return new ArrayList<>();
    }

    private static String decodeBase64(String ent) {
        return new String(Base64.getDecoder().decode(ent));
    }

    private static List<String> explode(String str, String regex) {
        if (str == null || str.length() == 0) {
            return new ArrayList<>();
        }

        return Arrays.asList(str.split(regex))
            .stream()
            .map(AgentData::decodeBase64)
            .collect(Collectors.toList());
    }
}
