package edu.hw6.Task6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;
import static java.util.Map.entry;

public final class Task6 {
    private static final String SPACE_FOR_NAMES = " ".repeat(3);
    private static final int SPACE_FOR_PORTS = 7;
    private static final int MAX_PORT_NUMBER = 49151;
    private static final int BUILDER_SIZE = 10;

    @SuppressWarnings("MultipleStringLiterals")
    private static final Map<Integer, String> SERVICES = Map.ofEntries(
        entry(21, "FTP"),
        entry(22, "SSH"),
        entry(25, "SMTP"),
        entry(53, "DNS"),
        entry(80, "HTTP"),
        entry(135, "EPMAP"),
        entry(137, "Служба имен NetBIOS"),
        entry(138, "Служба датаграмм NetBIOS"),
        entry(139, "Служба сеансов NetBIOS"),
        entry(445, "Microsoft-DS Active Directory"),
        entry(17500, "Dropbox"),
        entry(500, "IKE"),
        entry(3306, "MySQL Database"),
        entry(5432, "PostgreSQL Database"),
        entry(3389, "Remote Desktop Protocol (RDP)"),
        entry(27017, "MongoDB Database"),
        entry(1521, "Oracle Database"),
        entry(49152, "Windows RPC (Remote Procedure Call)"),
        entry(5353, "mDNS (Multicast Domain Name System)"),
        entry(5672, "AMQP (Advanced Message Queuing Protocol)"),
        entry(5355, "LLMNR (Link-Local Multicast Name Resolution)"),
        entry(49153, "Windows RPC (Remote Procedure Call)"),
        entry(23, "Telnet"),
        entry(110, "POP3"),
        entry(143, "IMAP"),
        entry(67, "DHCP"),
        entry(68, "DHCP"),
        entry(123, "NTP"),
        entry(8080, "HTTP Proxy"),
        entry(1080, "SOCKS"),
        entry(1433, "MSSQL"),
        entry(2049, "NFS")
    );

    private Task6() {
    }

    private static String checkTCP(int port) {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_SIZE);
        try (ServerSocket ignored = new ServerSocket(port)) {
            return "";
        } catch (IOException ioException) {
            stringBuilder.append("TCP ").append(" ".repeat(SPACE_FOR_PORTS)).append(port)
                .append(" ".repeat(SPACE_FOR_PORTS - String.valueOf(port).length()))
                .append(SERVICES.getOrDefault(port, "")).append("\n");
            return String.valueOf(stringBuilder);
        }
    }

    private static String checkUDP(int port) {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_SIZE);
        try (DatagramSocket ignored = new DatagramSocket(port)) {
            return "";
        } catch (IOException ioException) {
            stringBuilder.append("UDP ").append(" ".repeat(SPACE_FOR_PORTS)).append(port)
                .append(" ".repeat(SPACE_FOR_PORTS - String.valueOf(port).length()))
                .append(SERVICES.getOrDefault(port, "")).append("\n");
            return String.valueOf(stringBuilder);
        }
    }

    public static String getUsedPorts() {
        StringBuilder stringBuilder = new StringBuilder(BUILDER_SIZE);
        stringBuilder.append("Протокол").append(SPACE_FOR_NAMES).append("Порт").append(SPACE_FOR_NAMES)
            .append("Сервис\n");
        for (int i = 0; i < MAX_PORT_NUMBER; i++) {
            stringBuilder.append(checkTCP(i));
            stringBuilder.append(checkUDP(i));
        }
        return String.valueOf(stringBuilder);
    }
}
