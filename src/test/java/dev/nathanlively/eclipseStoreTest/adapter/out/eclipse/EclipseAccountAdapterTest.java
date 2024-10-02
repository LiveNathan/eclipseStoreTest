package dev.nathanlively.eclipseStoreTest.adapter.out.eclipse;

import dev.nathanlively.eclipseStoreTest.domain.Account;
import org.eclipse.store.storage.embedded.types.EmbeddedStorage;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class EclipseAccountAdapterTest {
    private static final Logger log = LoggerFactory.getLogger(EclipseAccountAdapterTest.class);
    @TempDir
    Path storageDir;
    private EclipseAccountAdapter adapter;
    private ApplicationContext mockContext;

    @ParameterizedTest
    @EnumSource(StorerType.class)
    void canWriteReadRestartAndReadAgain(StorerType storerType) {
        mockContext = mock(ApplicationContext.class);

        writeData(storerType);
        log.info("Storage manager shut down. Restarting...");
        readData();
    }

    private void writeData(StorerType storerType) {
        try (EmbeddedStorageManager storageManager = startStorageManager()) {
            adapter = new EclipseAccountAdapter(storageManager, mockContext);
            List<Account> accounts = adapter.findAll();
            assertThat(accounts).isEmpty();

            Account account = Account.create("Account A", "password");
            adapter.save(account, storerType);

            List<Account> allAccounts = adapter.findAll();
            assertThat(allAccounts).hasSize(1);
            assertThat(allAccounts.getFirst().username()).isEqualTo("Account A");
        }
    }

    private void readData() {
        try (EmbeddedStorageManager storageManager = startStorageManager()) {
            adapter = new EclipseAccountAdapter(storageManager, mockContext);
            List<Account> allAccounts = adapter.findAll();
            assertThat(allAccounts).hasSize(1);
            assertThat(allAccounts.getFirst().username()).isEqualTo("Account A");
        }
    }

    private EmbeddedStorageManager startStorageManager() {
        return EmbeddedStorage.start(storageDir);
    }

}