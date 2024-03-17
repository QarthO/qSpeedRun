package gg.quartzdev.qtemplateplugin.util;

import gg.quartzdev.qtemplateplugin.commands.CMD;
import gg.quartzdev.qtemplateplugin.commands.CMDreload;
import gg.quartzdev.qtemplateplugin.commands.CommandManager;
import gg.quartzdev.qtemplateplugin.commands.QCommand;
import gg.quartzdev.qtemplateplugin.listeners.ExampleListener;
import gg.quartzdev.qtemplateplugin.QTemplatePlugin;
import gg.quartzdev.qtemplateplugin.storage.Config;
import gg.quartzdev.qtemplateplugin.storage.QConfiguration;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QPlugin {

    public static QPlugin instance;
    private static QTemplatePlugin javaPlugin;
    private static Config config;
    private boolean selfDisabled;

    public static QTemplatePlugin getPlugin(){
        return javaPlugin;
    }

    public static Config getConfig(){
        return config;
    }

    private QPlugin(QTemplatePlugin plugin, boolean useConfig, int bStatsPluginId){
        javaPlugin = plugin;

        if(useConfig){
            setupPluginConfig();
        }

        if(bStatsPluginId > 0){
            setupMetrics(bStatsPluginId);
        }

        registerCommands();
        registerListeners();
    }

    public static void enable(QTemplatePlugin plugin, boolean useConfig, int bStatsPluginId){
        if(instance != null){
            QLogger.error(Messages.ERROR_PLUGIN_ENABLE);
            return;
        }
        instance = new QPlugin(plugin, useConfig, bStatsPluginId);
    }

    public static void disable(){

//        Warns about reloading
        final boolean isStopping = Bukkit.getServer().isStopping();
        if(!isStopping && !instance.selfDisabled){
            QLogger.warning(Messages.PLUGIN_UNSAFE_DISABLE);
        }

        QLogger.info(Messages.PLUGIN_DISABLE);
        instance = null;
        javaPlugin = null;
        config = null;

//        Put logic to stop any async tasks
    }

    public void setupMetrics(int pluginId){
        Metrics metrics = new Metrics(javaPlugin, pluginId);
    }

    @SuppressWarnings("UnstableApiUsage")
    public static String getVersion(){
        return javaPlugin.getPluginMeta().getVersion();
    }

    public static String getName(){
        return javaPlugin.getName();
    }

    private void createDataFolder(){
        try{
            javaPlugin.getDataFolder().mkdirs();
        } catch(SecurityException exception){
            QLogger.error(Messages.ERROR_CREATE_FILE.parse("file", "Plugin Data Folder"));
        }
    }

    public void setupPluginConfig(){
        createDataFolder();
        config = new Config("config.yml");
    }

    public void registerCommands(){
        List<String> aliases = new ArrayList<>();
        aliases.add("template");
        HashMap<String, QCommand> subCommands = new HashMap<>();
        subCommands.put("", new CMD("info", QPerm.GROUP_BASIC));
        subCommands.put("reload", new CMDreload("reload", QPerm.GROUP_ADMIN));
        new CommandManager(getName(), aliases, subCommands);
    }

    public void registerListeners(){
        Bukkit.getPluginManager().registerEvents(new ExampleListener(), javaPlugin);
    }

}
