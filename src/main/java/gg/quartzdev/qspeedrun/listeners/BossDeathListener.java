package gg.quartzdev.qspeedrun.listeners;

import gg.quartzdev.qspeedrun.util.Messages;
import gg.quartzdev.qspeedrun.util.QLogger;
import gg.quartzdev.qspeedrun.util.QPlugin;
import gg.quartzdev.qspeedrun.util.Sender;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.HashSet;

public class BossDeathListener implements Listener {

    public BossDeathListener(){
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event){
        if(QPlugin.getConfig().isDisabledWorld(event.getEntity().getWorld())){
            return;
        }

        if(!QPlugin.getConfig().isBossType(event.getEntityType())){
            return;
        }
        final LivingEntity entity = event.getEntity();
        final Player player = entity.getKiller();

        Messages msg = Messages.BROADCAST_BOSS_KILLED;

        if(player == null){
            msg = Messages.BROADCAST_BOSS_DEATH;
        }
        msg
                .parse("player_color", "<blue>")
                .parse("player_name", player.getName())
                .parse("boss_type", WordUtils.capitalizeFully(event.getEntityType().name()));

        Sender.broadcast(msg.get());
        Sound sound = Sound.sound(Key.key("block.note_block.banjo"), Sound.Source.BLOCK, 1f, 1f);
        Sender.broadcast(sound);
    }

}
