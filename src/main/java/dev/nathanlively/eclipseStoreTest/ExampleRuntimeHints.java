package dev.nathanlively.eclipseStoreTest;

import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

public class ExampleRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
//        hints.reflection().registerType(EmbeddedStorageManager.class, MemberCategory.values());
//        hints.reflection().registerType(org.eclipse.serializer.persistence.binary.org.eclipse.serializer.collections.BinaryHandlerEqBulkList.class, MemberCategory.values());
//        hints.reflection().registerType(org.eclipse.serializer.persistence.binary.types.BinaryPersistence.class, MemberCategory.values());
//        hints.reflection().registerType(org.eclipse.serializer.reflect.XReflect.class, MemberCategory.values());
    }
}
