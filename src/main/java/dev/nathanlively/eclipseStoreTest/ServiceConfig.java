package dev.nathanlively.eclipseStoreTest;

import dev.nathanlively.eclipseStoreTest.adapter.out.eclipse.EclipseAccountAdapter;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    public EclipseAccountAdapter eclipseAccountAdapter(EmbeddedStorageManager storageManager,
                                                       ApplicationContext context) {
        return new EclipseAccountAdapter(storageManager, context);
    }
}
