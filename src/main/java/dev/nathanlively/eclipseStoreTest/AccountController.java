package dev.nathanlively.eclipseStoreTest;

import dev.nathanlively.eclipseStoreTest.adapter.out.eclipse.EclipseAccountAdapter;
import dev.nathanlively.eclipseStoreTest.adapter.out.eclipse.StorerType;
import dev.nathanlively.eclipseStoreTest.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final EclipseAccountAdapter accountAdapter;

    @Autowired
    public AccountController(EclipseAccountAdapter accountAdapter) {
        this.accountAdapter = accountAdapter;
    }

    @GetMapping("/generate")
    public ResponseEntity<Void> generateNewAccount() {
        String randomUsername = "user-" + UUID.randomUUID();
        String randomHashedPassword = UUID.randomUUID().toString(); // You might want to use a real hash in a production app

        Account newAccount = Account.create(randomUsername, randomHashedPassword);
        accountAdapter.save(newAccount, StorerType.EAGER);

        return ResponseEntity.ok().build();
    }
}
