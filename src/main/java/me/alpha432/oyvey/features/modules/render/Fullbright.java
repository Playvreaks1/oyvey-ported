package skid.krypton.module.modules.render;

import skid.krypton.module.Category;
import skid.krypton.module.Module;
import skid.krypton.module.setting.ModeSetting;
import skid.krypton.module.setting.NumberSetting;
import skid.krypton.utils.EncryptedString;
import net.minecraft.world.LightType;

public final class Fullbright extends Module {
    private final NumberSetting minimumLightLevel = new NumberSetting(EncryptedString.of("Light Level"), 0.0, 15.0, 15.0, 1.0);
    private final ModeSetting<LightType> lightType = new ModeSetting<>("Light Type", LightType.BLOCK, LightType.class);

    public Fullbright() {
        super(EncryptedString.of("Full Bright"), EncryptedString.of("Makes you see in the dark as its scary"), -1, Category.RENDER);
        this.addSettings(this.minimumLightLevel, this.lightType);
    }

    @Override
    public void onEnable() {
        try { mc.worldRenderer.reload(); } catch (Exception ignored) {}
    }

    @Override
    public void onDisable() {
        try { mc.worldRenderer.reload(); } catch (Exception ignored) {}
    }

    public int getLuminance(LightType type) {
        if (!isEnabled() || type != lightType.getValue()) {
            return 0;
        }
        return minimumLightLevel.getIntValue();
    }

}