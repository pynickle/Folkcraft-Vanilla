package com.euphony.folkcraft_vanilla.common.item;

import com.euphony.folkcraft_vanilla.common.item.utils.PortableJukeboxSongPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.protocol.game.ClientboundStopSoundPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.JukeboxPlayable;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PortableJukeboxItem extends Item {
    boolean hasClientStop = false;
    boolean hasClientPlay = false;
    ItemStack discStack = ItemStack.EMPTY;
    public final PortableJukeboxSongPlayer portableJukeboxSongPlayer;

    public PortableJukeboxItem(Properties properties) {
        super(properties.component(DataComponents.JUKEBOX_PLAYABLE, null));
        this.portableJukeboxSongPlayer = new PortableJukeboxSongPlayer();
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);

        JukeboxPlayable jukeboxPlayable = stack.get(DataComponents.JUKEBOX_PLAYABLE);
        if (player.isShiftKeyDown()) {
            if(jukeboxPlayable == null
                    && usedHand == InteractionHand.MAIN_HAND) {
                ItemStack offhandStack = player.getOffhandItem();
                JukeboxPlayable musicDiscSong = offhandStack.get(DataComponents.JUKEBOX_PLAYABLE);
                discStack = offhandStack.copy();
                Optional<Holder<JukeboxSong>> optionalSong = musicDiscSong != null ? musicDiscSong.song().unwrap(level.registryAccess()) : Optional.empty();
                if(optionalSong.isPresent()) {
                    stack.set(DataComponents.JUKEBOX_PLAYABLE, musicDiscSong);
                    this.portableJukeboxSongPlayer.setSongWithoutPlaying(optionalSong.get(), 0);
                    offhandStack.consume(1, player);
                    return InteractionResultHolder.success(stack);
                }
            } else if(jukeboxPlayable != null)  {
                if(!player.addItem(discStack)) {
                    player.drop(discStack, false);
                }
                stack.set(DataComponents.JUKEBOX_PLAYABLE, null);
                discStack = ItemStack.EMPTY;

                return InteractionResultHolder.success(stack);
            }
        } else {
            if(jukeboxPlayable != null) {
                jukeboxPlayable.song().unwrap(level.registryAccess()).ifPresent((song) -> {
                    if(level.isClientSide) {
                        if (portableJukeboxSongPlayer.isPlaying()) {
                            hasClientStop = true;
                            portableJukeboxSongPlayer.stop();
                        } else {
                            hasClientPlay = true;
                            Minecraft.getInstance().gui.setNowPlaying(song.value().description());
                            portableJukeboxSongPlayer.play(player, level, song);
                        }
                    } else {
                        if (hasClientStop) {
                            hasClientStop = false;
                            ((ServerLevel) level).getServer().getPlayerList().broadcast(player, player.getX(), player.getY(), player.getZ(),
                                    (song.value().soundEvent().value()).getRange(1.0f), level.dimension(), new ClientboundStopSoundPacket(
                                            song.value().soundEvent().value().getLocation(), SoundSource.PLAYERS
                                    ));
                            portableJukeboxSongPlayer.stop();
                        } else if(hasClientPlay) {
                            hasClientPlay = false;
                            portableJukeboxSongPlayer.play(player, level, song);
                        }
                    }
                });
                return InteractionResultHolder.success(stack);
            }
            return InteractionResultHolder.pass(stack);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected) {
        this.portableJukeboxSongPlayer.tick();
        super.inventoryTick(stack, level, entity, slotId, isSelected);
    }
}
