package com.euphony.folkcraft_vanilla.common.item.utils;


import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;


public class PortableJukeboxSongPlayer {
    private long ticksSinceSongStarted;
    @Nullable
    private Holder<JukeboxSong> song;

    public PortableJukeboxSongPlayer() {
    }

    public boolean isPlaying() {
        return this.song != null;
    }

    @Nullable
    public JukeboxSong getSong() {
        return this.song == null ? null : this.song.value();
    }

    public long getTicksSinceSongStarted() {
        return this.ticksSinceSongStarted;
    }

    public void setSongWithoutPlaying(Holder<JukeboxSong> song, long ticksSinceSongStarted) {
        if (!song.value().hasFinished(ticksSinceSongStarted)) {
            this.song = song;
            this.ticksSinceSongStarted = ticksSinceSongStarted;
        }
    }

    public void play(Player player, Level level, Holder<JukeboxSong> song) {
        this.song = song;
        this.ticksSinceSongStarted = 0L;
        level.playSound(player, player, this.song.value().soundEvent().value(), player.getSoundSource(), 1.0F, 1.0F);
    }

    public void stop() {
        if (this.song != null) {
            Minecraft.getInstance().getSoundManager().stop(this.song.value().soundEvent().value().getLocation(), SoundSource.PLAYERS);
            this.song = null;
            this.ticksSinceSongStarted = 0L;
        }
    }

    public void tick() {
        if (this.song != null) {
            if (this.song.value().hasFinished(this.ticksSinceSongStarted)) {
                this.stop();
            } else {
                ++this.ticksSinceSongStarted;
            }
        }
    }
}
