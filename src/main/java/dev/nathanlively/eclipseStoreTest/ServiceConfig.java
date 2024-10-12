package dev.nathanlively.eclipseStoreTest;

import dev.nathanlively.eclipseStoreTest.adapter.out.eclipse.EclipseAccountAdapter;
import org.eclipse.store.integrations.spring.boot.types.configuration.EclipseStoreProperties;
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageFoundationFactory;
import org.eclipse.store.integrations.spring.boot.types.factories.EmbeddedStorageManagerFactory;
import org.eclipse.store.storage.embedded.types.EmbeddedStorage;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageFoundation;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    private static final Logger log = LoggerFactory.getLogger(ServiceConfig.class);

    @Bean
    public EmbeddedStorageManager injectStorageTest(EclipseStoreProperties myConfiguration,
                                                    EmbeddedStorageManagerFactory managerFactory,
                                                    EmbeddedStorageFoundationFactory foundationFactory) {
        log.info("Initializing EmbeddedStorageManager...");
        EmbeddedStorageFoundation<?> storageFoundation = foundationFactory.createStorageFoundation(myConfiguration);
        return managerFactory.createStorage(storageFoundation, true);
    }

    @Bean
    public EclipseAccountAdapter eclipseAccountAdapter(EmbeddedStorageManager storageManager,
                                                       ApplicationContext context) {
        return new EclipseAccountAdapter(storageManager, context);
    }
}
