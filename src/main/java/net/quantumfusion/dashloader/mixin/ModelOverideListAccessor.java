package net.quantumfusion.dashloader.mixin;

import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ModelOverrideList.class)
public interface ModelOverideListAccessor {


    @Accessor
    ModelOverrideList.BakedOverride[] getOverrides();

    @Accessor
    @Mutable
    void setOverrides(ModelOverrideList.BakedOverride[] overrides);

    @Accessor
    Identifier[] getConditionTypes();

    @Accessor
    @Mutable
    void setConditionTypes(Identifier[] conditionTypes);
}
