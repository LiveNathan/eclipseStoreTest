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
    public ResponseEntity<String> generateNewAccount() {
        String randomUsername = "user-" + UUID.randomUUID();
        String randomHashedPassword = UUID.randomUUID().toString();

        Account newAccount = Account.create(randomUsername, randomHashedPassword);
        int count = accountAdapter.save(newAccount, StorerType.EAGER);

        String successMessage = String.format("Account with username %s created. Total accounts: %s", randomUsername, count);
        return ResponseEntity.ok(successMessage);
    }
}