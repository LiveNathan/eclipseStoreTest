package dev.nathanlively.eclipseStoreTest;

import dev.nathanlively.eclipseStoreTest.adapter.out.eclipse.EclipseAccountAdapter;
import org.eclipse.serializer.collections.EqHashEnum;
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageFoundation;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.aot.hint.MemberCategory;
import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;

import java.util.Optional;

public class ExampleRuntimeHints implements RuntimeHintsRegistrar {
    @Override
    public void registerHints(RuntimeHints hints, ClassLoader classLoader) {
        hints.reflection().registerType(EmbeddedStorageFoundation.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS);
        hints.reflection().registerType(EmbeddedStorageFoundationFactory.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS);

        hints.reflection().registerType(EclipseAccountAdapter.class, MemberCategory.values());
        hints.reflection().registerType(EmbeddedStorageManager.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS);
        hints.reflection().registerType(org.eclipse.serializer.persistence.binary.org.eclipse.serializer.collections.BinaryHandlerEqBulkList.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS, MemberCategory.DECLARED_FIELDS);
        hints.reflection().registerType(org.eclipse.serializer.collections.EqBulkList.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.DECLARED_FIELDS);
        hints.reflection().registerType(org.eclipse.serializer.equality.Equalator.class, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.DECLARED_FIELDS);
        hints.reflection().registerType(org.eclipse.serializer.persistence.binary.types.BinaryPersistence.class, MemberCategory.values());
        hints.reflection().registerType(org.eclipse.serializer.reflect.XReflect.class, MemberCategory.values());
        hints.reflection().registerType(Optional.class, MemberCategory.PUBLIC_FIELDS, MemberCategory.INVOKE_PUBLIC_METHODS);
        hints.reflection().registerType(EqHashEnum.class, MemberCategory.PUBLIC_FIELDS, MemberCategory.DECLARED_FIELDS, MemberCategory.INVOKE_PUBLIC_CONSTRUCTORS, MemberCategory.INVOKE_PUBLIC_METHODS, MemberCategory.INVOKE_DECLARED_METHODS);
    }
}
