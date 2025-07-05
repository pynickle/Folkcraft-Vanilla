package com.euphony.folkcraft_vanilla.event;

import com.euphony.folkcraft_vanilla.event.event.CompressedSlimeBlockEvent;
import com.euphony.folkcraft_vanilla.event.event.PortableJukeboxEvent;
import dev.architectury.event.events.common.BlockEvent;
import dev.architectury.event.events.common.PlayerEvent;

public class FCEvents {
    public static void init() {
        BlockEvent.FALLING_LAND.register(CompressedSlimeBlockEvent::fallingLand);

        PlayerEvent.DROP_ITEM.register(PortableJukeboxEvent::dropItem);
    }
}
