package net.quantumfusion.dashloader.mixin;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.CustomValue;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.quantumfusion.dashloader.DashLoader;
import net.quantumfusion.dashloader.api.feature.FeatureHandler;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        FeatureHandler.init();
        for (ModContainer mod : FabricLoader.getInstance().getAllMods()) {
            final ModMetadata metadata = mod.getMetadata();
            if (metadata.containsCustomValue("dashloader:disablefeature")) {
                final CustomValue customValue = metadata.getCustomValue("dashloader:disablefeature");
                customValue.getAsArray().forEach(value -> {
                    final String feature = value.getAsString();
                    FeatureHandler.disableFeature(feature);
                    DashLoader.LOGGER.warn("Disabled " + feature + " from mod:" + metadata.getName());
                });
            }
        }
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        return FeatureHandler.active(mixinClassName);
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
