package gg.quartzdev.qtemplateplugin.util;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Sender {
    public static void message(CommandSender sender, String message){
        if(sender instanceof Player)
            sender.sendMessage(parse(message, false));
        else
            sender.sendMessage(parse(message, true));
    }


    /**
     * Sends a Message to the player in chat
     * @param sender the {@link CommandSender} to send the message to
     * @param message the {@link Messages} to send
     */
    public static void message(CommandSender sender, Messages message){
        message(sender, message.get());
    }


    /**
     * Sends a message to the player in chat
     * @param player the {@link Player} to send the message to
     * @param message the message to send
     */
    public static void message(Player player, Messages message){
        message(player, message.get());
    }

    /**
     * Sends a message to the player in chat
     * @param player the {@link Player} to send the message to
     * @param message the message to send
     */
    public static void message(Player player, String message){
        player.sendMessage(parse(message, false));

    }

    /**
     * Sends an action bar to a player
     * @param player the player to send the actionbar to
     * @param message the message to send
     */
    public static void actionBar(Player player, String message){
        player.sendActionBar(parse(message, false));
    }

    /**
     * Deserializes the message and parses any MiniMessage placeholders and prepends the plugin prefix
     * @param message the message to parse
     * @param isConsole if the prepended plugin prefix shuold be the console prefix instead of the chat prefix
     * @return A MiniMessage deserialized chat Component
     */
    private static Component parse(String message, boolean isConsole){
        MiniMessage mm = MiniMessage.miniMessage();

        if(isConsole)
            return mm.deserialize(message,
                    Placeholder.parsed("prefix", Messages.CONSOLE_PREFIX.get())
            );
        return mm.deserialize(message,
                Placeholder.parsed("prefix", Messages.CHAT_PREFIX.get())
        );
    }

}
