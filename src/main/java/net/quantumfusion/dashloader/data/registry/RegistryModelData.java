package net.quantumfusion.dashloader.data.registry;

import io.activej.serializer.annotations.Deserialize;
import io.activej.serializer.annotations.Serialize;
import io.activej.serializer.annotations.SerializeSubclasses;
import net.quantumfusion.dashloader.model.DashModel;
import net.quantumfusion.dashloader.util.serialization.Pointer2ObjectMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegistryModelData {
    @Serialize(order = 0)
    @SerializeSubclasses(path = {0, 0}, extraSubclassesId = "models")
    public Pointer2ObjectMap<Pointer2ObjectMap<DashModel>> models;

    public RegistryModelData(@Deserialize("models") Pointer2ObjectMap<Pointer2ObjectMap<DashModel>> models) {
        this.models = models;
    }


    public List<Map<Integer, DashModel>> toUndash() {
        List<Map<Integer, DashModel>> list = new ArrayList<>(models.size());
        models.forEach(entry -> list.add(entry.key, entry.value.convert()));
        return list;
    }
}
