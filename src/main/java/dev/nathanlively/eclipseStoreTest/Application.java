package dev.nathanlively.eclipseStoreTest;

import dev.nathanlively.eclipseStoreTest.adapter.out.eclipse.EclipseAccountAdapter;
import dev.nathanlively.eclipseStoreTest.domain.Account;
import dev.nathanlively.eclipseStoreTest.domain.DataRoot;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportRuntimeHints;

@ImportRuntimeHints(ExampleRuntimeHints.class)  // Experiment 2
@RegisterReflectionForBinding({DataRoot.class, Account.class})  // Experiment 1
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}

