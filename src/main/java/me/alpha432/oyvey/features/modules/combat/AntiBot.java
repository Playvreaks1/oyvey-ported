package dev.zprestige.prestige.client.module.impl.combat;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.client.event.EventListener;
import dev.zprestige.prestige.client.event.impl.BotEvent;
import dev.zprestige.prestige.client.event.impl.PacketReceiveEvent;
import dev.zprestige.prestige.client.module.Category;
import dev.zprestige.prestige.client.module.Module;
import dev.zprestige.prestige.client.setting.impl.MultipleSetting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityPositionS2CPacket;

public class Antibot extends Module {

    public MultipleSetting setting;

    public Antibot() {
        super("Anti Bot", Category.Combat, "Removes bots from the game");
        setting = this.setting("Mode", new String[]{"Packet"}, new Boolean[]{true}).description("Mode to use to detect bots");
    }


    @Event lister
    public void event(World event) {
        event.setCancelled();
    }

    @Event lister
    public void event(PacketReceiveEvent event) {
        if (setting.getValue("Packet") && event.getPacket() instanceof EntityPositionS2CPacket && Getmc().world != null) {
            Entity entity = Getmc().world.getEntityById(((EntityPositionS2CPacket)event.getPacket()).can());
            if (entity == null) {
                return;
            }
            if (entity instanceof PlayerEntity && !Prestige.Companion.getAntiBotManager().getBots().contains(entity)) {
                Prestige.Companion.getAntiBotManager().addBot((PlayerEntity)entity);
            }
        }
    }
}
