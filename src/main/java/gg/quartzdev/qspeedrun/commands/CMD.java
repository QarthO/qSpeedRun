package gg.quartzdev.qspeedrun.commands;

import gg.quartzdev.qspeedrun.util.QPerm;
import gg.quartzdev.qspeedrun.util.QPlugin;
import gg.quartzdev.qspeedrun.util.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import java.util.List;

public class CMD extends QCommand{
    public CMD(String name, QPerm permissionGroup) {
        super(name, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        List<EntityType> schemaVersion = QPlugin.getConfig().getBossTypes();
        Sender.message(sender, "Bosses: " + schemaVersion.toString());
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
