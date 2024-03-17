package gg.quartzdev.qspeedrun.commands;

import gg.quartzdev.qspeedrun.util.QPerm;
import gg.quartzdev.qspeedrun.util.QPlugin;
import gg.quartzdev.qspeedrun.util.Sender;
import org.bukkit.command.CommandSender;

public class CMD extends QCommand{
    public CMD(String name, QPerm permissionGroup) {
        super(name, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        double schemaVersion = QPlugin.getConfig().getSchema();
        Sender.message(sender, "Schema Version: " + schemaVersion);
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
