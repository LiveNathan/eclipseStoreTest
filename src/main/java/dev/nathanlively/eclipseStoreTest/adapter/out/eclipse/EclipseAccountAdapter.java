package dev.nathanlively.eclipseStoreTest.adapter.out.eclipse;

import dev.nathanlively.eclipseStoreTest.domain.Account;
import dev.nathanlively.eclipseStoreTest.domain.DataRoot;
import org.eclipse.serializer.persistence.types.Storer;
import org.eclipse.store.integrations.spring.boot.types.concurrent.Read;
import org.eclipse.store.integrations.spring.boot.types.concurrent.Write;
import org.eclipse.store.storage.embedded.types.EmbeddedStorageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aot.hint.annotation.Reflective;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

//@RegisterReflectionForBinding(EclipseAccountAdapter.class)  // Experiment 4
//@Reflective  // Experiment 3
public class EclipseAccountAdapter {
    private final EmbeddedStorageManager storageManager;
    private static final Logger log = LoggerFactory.getLogger(EclipseAccountAdapter.class);
    private final ApplicationContext context;

    public EclipseAccountAdapter(EmbeddedStorageManager storageManager, ApplicationContext context) {
        this.storageManager = storageManager;
        this.context = context;
        initializeRoot();
    }

    private void initializeRoot() {
        if (storageManager.root() == null) {
            log.info("Initializing root.");
            storageManager.setRoot(new DataRoot());
            storageManager.storeRoot();
        }
    }

    @Write
    public void save(Account account, StorerType storerType) {
        getRoot().accounts().add(account);
        store(getRoot().accounts().usernameToAccount(), storerType);
    }

    @Read
    public List<Account> findAll() {
        return new ArrayList<>(getRoot().accounts().all());
    }

    private DataRoot getRoot() {
        return (DataRoot) storageManager.root();
    }

    private void store(final Object object, StorerType storerType) {
        try {
            switch (storerType) {
                case LAZY: {
                    Storer lazyStorer = storageManager.createLazyStorer();
                    lazyStorer.store(object);
                    lazyStorer.commit();
                    break;
                }
                case EAGER: {
                    Storer eagerStorer = storageManager.createEagerStorer();
                    eagerStorer.store(object);
                    eagerStorer.commit();
                }
                default:
                    storageManager.store(object);
            }
        } catch (final Throwable t) {
            onStorageFailure(t);
        }
    }

    public void storeAll(final Object... objects) {
        try {
            storageManager.storeAll(objects);
        } catch (final Throwable t) {
            onStorageFailure(t);
        }
    }

    public void storeAll(Iterable<?> iterable) {
        try {
            storageManager.storeAll(iterable);
        } catch (final Throwable t) {
            onStorageFailure(t);
        }
    }

    private void onStorageFailure(final Throwable t) {
        if (storageManager != null && storageManager.isRunning()) {
            try {
                log.error("Storage error! Shutting down storage...", t);
                SpringApplication.exit(context, () -> 0);
            } catch (final Throwable throwable) {
                log.error(throwable.getMessage(), throwable);
            }
        }
        DataRoot root = getRoot();
        if (root != null) {
            root.clear();
        }
    }
}
