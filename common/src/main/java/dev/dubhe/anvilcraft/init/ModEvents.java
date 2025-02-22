package dev.dubhe.anvilcraft.init;

import dev.dubhe.anvilcraft.AnvilCraft;
import dev.dubhe.anvilcraft.event.AnvilEventListener;
import dev.dubhe.anvilcraft.event.AnvilHitBlockPlacerEventListener;
import dev.dubhe.anvilcraft.event.AnvilHitPiezoelectricCrystalBlockEventListener;
import dev.dubhe.anvilcraft.event.AnvilHurtVillagerEventListener;
import dev.dubhe.anvilcraft.event.LightningEventListener;
import dev.dubhe.anvilcraft.event.PlayerEventListener;
import dev.dubhe.anvilcraft.event.ServerEventListener;

public class ModEvents {
    /**
     * 注册模组事件
     */
    public static void register() {
        AnvilCraft.EVENT_BUS.register(new AnvilEventListener());
        AnvilCraft.EVENT_BUS.register(new LightningEventListener());
        AnvilCraft.EVENT_BUS.register(new ServerEventListener());
        AnvilCraft.EVENT_BUS.register(new PlayerEventListener());
        AnvilCraft.EVENT_BUS.register(new AnvilHitPiezoelectricCrystalBlockEventListener());
        AnvilCraft.EVENT_BUS.register(new AnvilHurtVillagerEventListener());
        AnvilCraft.EVENT_BUS.register(new AnvilHitBlockPlacerEventListener());
    }
}
