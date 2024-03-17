package gg.quartzdev.qtemplateplugin.storage;

import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Config extends QConfiguration {

    private final String PATH_DISABLED_WORLDS = "disabled-worlds";
    private List<World> disabledWorlds;

    public Config(String fileName) {
        super(fileName);
        disabledWorlds = new ArrayList<>();
    }

    public void loadAllData(){
        loadDisabledWorlds();
    }
    public void saveAllData(){
        saveDisabledWorlds();
    }

    public void loadDisabledWorlds(){
        disabledWorlds = getWorldList(PATH_DISABLED_WORLDS);
    }
    public boolean isDisabledWorld(World world){
        return disabledWorlds.contains(world);
    }
    public void saveDisabledWorlds(){
        List<String> worldNames = disabledWorlds.stream().map(World::getName).collect(Collectors.toList());
        yamlConfiguration.set(PATH_DISABLED_WORLDS, worldNames);
    }
}
