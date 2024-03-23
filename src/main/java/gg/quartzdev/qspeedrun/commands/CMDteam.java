package gg.quartzdev.qspeedrun.commands;

import gg.quartzdev.qspeedrun.util.QPerm;
import org.bukkit.command.CommandSender;

public class CMDteam extends QCommand{
    public CMDteam(String name, QPerm permissionGroup) {
        super(name, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
