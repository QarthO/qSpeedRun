package gg.quartzdev.qspeedrun.commands;

import gg.quartzdev.qspeedrun.util.Messages;
import gg.quartzdev.qspeedrun.util.QPerm;
import gg.quartzdev.qspeedrun.util.QPlugin;
import gg.quartzdev.qspeedrun.util.Sender;
import org.bukkit.command.CommandSender;

public class CMDreload extends QCommand{
    public CMDreload(String name, QPerm permissionGroup) {
        super(name, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        QPlugin.getConfig().reload();
        Sender.message(sender, Messages.RELOAD_COMPLETE);
        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
