package com.euphony.folkcraft_vanilla;

import com.euphony.folkcraft_vanilla.common.init.FCBlocks;
import com.euphony.folkcraft_vanilla.common.init.FCCreativeTabs;
import com.euphony.folkcraft_vanilla.common.init.FCItems;
import com.euphony.folkcraft_vanilla.event.FCEvents;

public final class FolkcraftVanilla {
    public static final String MOD_ID = "fc_vanilla";

    public static void init() {
        FCBlocks.BLOCKS.register();
        FCItems.ITEMS.register();

        FCCreativeTabs.TABS.register();

        FCEvents.init();
    }
}
