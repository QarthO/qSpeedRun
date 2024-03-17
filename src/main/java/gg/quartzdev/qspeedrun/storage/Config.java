package gg.quartzdev.qspeedrun.storage;

import org.bukkit.World;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Config extends QConfiguration {

    private final String PATH_DISABLED_WORLDS = "disabled-worlds";
    private final String PATH_BOSS_TYPES = "boss-types";
    private List<World> disabledWorlds;
    private List<EntityType> bossTypes;

    public Config(String fileName) {
        super(fileName);
        disabledWorlds = new ArrayList<>();
        bossTypes = new ArrayList<>();
    }

    public void loadAllData(){
        loadDisabledWorlds();
        loadBossTypes();
    }
    public void saveAllData(){
        saveDisabledWorlds();
        saveBossTypes();
    }

    public void loadDisabledWorlds(){
        disabledWorlds = getWorldList(PATH_DISABLED_WORLDS);
    }
    public boolean isDisabledWorld(World world){
        return disabledWorlds.contains(world);
    }
    public void saveDisabledWorlds(){
        List<String> worldNames = disabledWorlds.stream().map(World::getName).toList();
        yamlConfiguration.set(PATH_DISABLED_WORLDS, worldNames);
        save();
    }

    public void loadBossTypes(){
        bossTypes = getEntityTypeList(PATH_BOSS_TYPES);
    }
    public boolean isBossType(EntityType type){
        return bossTypes.contains(type);
    }
    public void saveBossTypes(){
        List<String> bossNames = bossTypes.stream().map(EntityType::name).toList();;
        yamlConfiguration.set(PATH_BOSS_TYPES, bossNames);
        save();
    }
    public List<EntityType> getBossTypes(){
        return List.copyOf(bossTypes);
    }
}
