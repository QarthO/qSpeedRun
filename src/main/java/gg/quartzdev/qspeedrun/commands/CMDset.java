package gg.quartzdev.qspeedrun.commands;

import gg.quartzdev.qspeedrun.util.QPerm;
import org.bukkit.command.CommandSender;

public class CMDset extends QCommand{
    public CMDset(String name, QPerm permissionGroup) {
        super(name, permissionGroup);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {

        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
