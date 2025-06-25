package com.euphony.folkcraft_vanilla.event;

import com.euphony.folkcraft_vanilla.event.event.CompressedSlimeBlockEvent;
import dev.architectury.event.events.common.BlockEvent;

public class FCEvents {
    public static void init() {
        BlockEvent.FALLING_LAND.register(CompressedSlimeBlockEvent::fallingLand);
    }
}
