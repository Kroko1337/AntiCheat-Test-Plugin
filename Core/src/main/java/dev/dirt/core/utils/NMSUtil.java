package dev.dirt.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NMSUtil {
    private static String version = "";

    private static Method getHandle;

    private static Method sendPacket;

    private static Field playerConnection;

    private static Class<?> chatSerializer;

    private static Class<?> iIChatBaseComponent;

    private static Class<?> packetPlayOutChat;

    private static Class<?> packetPlayOutPlayerListHeaderFooter;

    static {
        try {
            version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
            getHandle = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer").getMethod("getHandle", new Class[0]);
            sendPacket = Class.forName("net.minecraft.server." + version + ".PlayerConnection").getMethod("sendPacket", new Class[] { Class.forName("net.minecraft.server." + version + ".Packet") });
            playerConnection = Class.forName("net.minecraft.server." + version + ".EntityPlayer").getField("playerConnection");
            chatSerializer = Class.forName("net.minecraft.server." + version + ".IChatBaseComponent$ChatSerializer");
            iIChatBaseComponent = Class.forName("net.minecraft.server." + version + ".IChatBaseComponent");
            packetPlayOutChat = Class.forName("net.minecraft.server." + version + ".PacketPlayOutChat");
            packetPlayOutPlayerListHeaderFooter = Class.forName("net.minecraft.server." + version + ".PacketPlayOutPlayerListHeaderFooter");
        } catch (ClassNotFoundException|NoSuchMethodException|NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void sendActionBarMessage(String message, Player player) {
        Object packet = null;
        try {
            Object serialized = chatSerializer.getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + message + "\"}" });
            packet = packetPlayOutChat.getConstructor(new Class[] { iIChatBaseComponent, byte.class }).newInstance(new Object[] { serialized, Byte.valueOf((byte)2) });
            sendPacket(player, packet);
            sendPacket(player, packet);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException|InstantiationException e) {
            e.printStackTrace();
        }
    }

    public static void sendTabList(String header, String footer, Player player) {
        try {
            Object serializedHeader = chatSerializer.getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + header + "\"}" });
            Object serializedFooter = chatSerializer.getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\": \"" + footer + "\"}" });
            Object packet = packetPlayOutPlayerListHeaderFooter.getConstructor(new Class[] { iIChatBaseComponent }).newInstance(new Object[] { serializedHeader });
            Field field = packet.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(packet, serializedFooter);
            field.setAccessible(false);
            sendPacket(player, packet);
        } catch (NoSuchMethodException|InvocationTargetException|IllegalAccessException|InstantiationException|NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static void sendPacket(Player player, Object packet) throws InvocationTargetException, IllegalAccessException {
        Object p = getHandle.invoke(player, new Object[0]);
        Object connection = playerConnection.get(p);
        sendPacket.invoke(connection, new Object[] { packet });
    }
}
