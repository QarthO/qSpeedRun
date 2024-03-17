package gg.quartzdev.qtemplateplugin.commands;

import gg.quartzdev.qtemplateplugin.util.QPerm;
import gg.quartzdev.qtemplateplugin.util.QPlugin;
import gg.quartzdev.qtemplateplugin.util.Sender;
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
