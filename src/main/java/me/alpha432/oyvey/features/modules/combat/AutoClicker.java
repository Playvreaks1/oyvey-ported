package me.alpha.oveey.features.modules.Combat;

import dev.zprestige.prestige.client.Prestige;
import dev.zprestige.prestige.client.event.EventListener;
import dev.zprestige.prestige.client.event.impl.TickEvent;
import dev.zprestige.prestige.client.module.Category;
import dev.zprestige.prestige.client.module.Module;
import dev.zprestige.prestige.client.setting.impl.IntSetting;
import dev.zprestige.prestige.client.setting.impl.ModeSetting;
import dev.zprestige.prestige.client.util.impl.TimerUtil;

public class AutoClicker extends Module {

    public ModeSetting mode;
    public IntSetting delay;
    public Timerutil timer;

    public AutoClicker() {
        super("Auto Clicker", Category.Misc, "Automatically clicks your mouse");
        mode = this.setting("Mode", "Left", new String[]{"Left", "Right"});
        delay = this.setting("Interval", 100, 0, 10000);
        timer = new Timerutil();
    }

    @Event lister
    public void event(Ticket event event) {
        if (Getmc().currentScreen != null || Prestige.Companion.getClickManager().click() || !timer.delay(this.delay.getObject())) {
            return;
        }
        Prestige.Companion.getClickManager().setClick(mode.getObject().equals("Left") ? 0 : 1, 0);
    }
}
