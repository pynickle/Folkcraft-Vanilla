package com.euphony.folkcraft_vanilla.event.event;

import com.euphony.folkcraft_vanilla.common.init.FCItems;
import com.euphony.folkcraft_vanilla.common.item.PortableJukeboxItem;
import com.euphony.folkcraft_vanilla.common.item.utils.PortableJukeboxSongPlayer;
import dev.architectury.event.EventResult;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxPlayable;
import net.minecraft.world.level.Level;

import java.util.logging.Logger;

public class PortableJukeboxEvent {
    public static EventResult dropItem(Player player, ItemEntity itemEntity) {
        ItemStack stack = itemEntity.getItem();

        if(stack.is(FCItems.PORTABLE_JUKEBOX.get())) {
            PortableJukeboxItem jukeboxStack = (PortableJukeboxItem) stack.getItem();
            PortableJukeboxSongPlayer portableJukeboxSongPlayer = jukeboxStack.portableJukeboxSongPlayer;
            JukeboxPlayable jukeboxPlayable = stack.get(DataComponents.JUKEBOX_PLAYABLE);
            Level level = player.level();

            if (jukeboxPlayable != null) {
                jukeboxPlayable.song().unwrap(level.registryAccess()).ifPresent((song) -> {
                    ((ServerLevel) level).getServer().getPlayerList().broadcast(null, player.getX(), player.getY(), player.getZ(),
                            (song.value().soundEvent().value()).getRange(1.0f), level.dimension(), new ClientboundStopSoundPacket(
                                    song.value().soundEvent().value().getLocation(), SoundSource.PLAYERS
                            ));
                    portableJukeboxSongPlayer.stop();
                });
                return EventResult.interruptTrue();
            }
        }
        return EventResult.pass();
    }
}
