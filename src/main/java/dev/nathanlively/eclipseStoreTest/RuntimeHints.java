package dev.nathanlively.eclipseStoreTest;

import dev.nathanlively.eclipseStoreTest.adapter.out.eclipse.EclipseAccountAdapter;
import org.apache.el.stream.Optional;
import org.eclipse.serializer.collections.EqHashEnum;
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageFoundation;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.aot.hint.TypeReference;

public class RuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(org.springframework.aot.hint.RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(EmbeddedStorageFoundation.class, MemberCategory.values());
        hints.reflection().registerType(EmbeddedStorageFoundationFactory.class, MemberCategory.values());
        hints.reflection().registerType(EclipseAccountAdapter.class, MemberCategory.values());
        hints.reflection().registerType(EmbeddedStorageManager.class, MemberCategory.values());
        hints.reflection().registerType(org.eclipse.serializer.persistence.binary.org.eclipse.serializer.collections.BinaryHandlerEqBulkList.class, MemberCategory.values());
        hints.reflection().registerType(org.eclipse.serializer.collections.EqBulkList.class, MemberCategory.values());
        hints.reflection().registerType(org.eclipse.serializer.equality.Equalator.class, MemberCategory.values());
        hints.reflection().registerType(org.eclipse.serializer.persistence.binary.types.BinaryPersistence.class, MemberCategory.values());
        hints.reflection().registerType(org.eclipse.serializer.reflect.XReflect.class, MemberCategory.values());
        hints.reflection().registerType(Optional.class, MemberCategory.values());
        hints.reflection().registerType(EqHashEnum.class, MemberCategory.values());
        hints.reflection().registerType(TypeReference.of("java.util.Collections$SetFromMap"), builder -> builder.withMembers(MemberCategory.values()));
    }
}
