package engine.account;

import engine.account.models.AccountDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentAccountService {

    private final AccountService accountService;

    public CurrentAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    public AccountDto getCurrentAccount() {
        var auth = SecurityContextHolder.getContext()
                                        .getAuthentication();
        if (!auth.isAuthenticated()) {
            throw new IllegalStateException();
        }

        var userDetails = (UserDetails) auth.getPrincipal();
        AccountDto account = null;
        try {
            account = accountService.getAccount(userDetails.getUsername());
        } catch (AccountNotFoundException ignored) {
        }

        return account;
    }
}
