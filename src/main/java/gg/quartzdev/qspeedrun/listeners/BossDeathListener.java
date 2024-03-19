package gg.quartzdev.qspeedrun.listeners;

import gg.quartzdev.qspeedrun.util.Messages;
import gg.quartzdev.qspeedrun.util.QPlugin;
import gg.quartzdev.qspeedrun.util.Sender;
import org.apache.commons.text.WordUtils;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

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
        Player killer = guessKiller(event.getEntity());
        Messages msg = Messages.BROADCAST_BOSS_DEATH;

        if(killer != null){
            msg = Messages.BROADCAST_BOSS_KILLED
                    .parse("player_color", "<blue>")
                    .parse("player_name", killer.getName());

        }
        msg.parse("boss_type", WordUtils.capitalizeFully(event.getEntityType().name()));

        Sender.broadcast(msg.get());
        Sender.broadcast(QPlugin.getConfig().getBossAlert());
    }

    private @Nullable Player guessKiller(LivingEntity entity){
        if(entity.getKiller() != null){
            return entity.getKiller();
        }
        Collection<Player> players = entity.getLocation().getNearbyPlayers(100);
        Player closestPlayer = null;
        double closestDistance = 0;
        for(Player player : players){
            double distance = entity.getLocation().distanceSquared(player.getLocation());
            if(closestPlayer == null || distance <= closestDistance){
                closestPlayer = player;
                closestDistance = distance;
            }
        }
        return closestPlayer;
    }

}
