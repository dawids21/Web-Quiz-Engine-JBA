package engine.account;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CurrentAccountService {

    private final AccountDao accountDao;

    public CurrentAccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
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
            account = accountDao.getAccount(userDetails.getUsername());
        } catch (AccountNotFoundException ignored) {
        }

        return account;
    }
}
