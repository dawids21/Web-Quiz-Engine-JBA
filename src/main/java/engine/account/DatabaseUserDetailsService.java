package engine.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    private final AccountDao accountDao;

    @Autowired
    public DatabaseUserDetailsService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    //TODO what does @Transactional mean?
    @Transactional
    public UserDetails loadUserByUsername(String username)
             throws UsernameNotFoundException {
        AccountDto account = null;
        try {
            account = accountDao.getAccount(username);
        } catch (AccountNotFoundException e) {
            throw new UsernameNotFoundException(
                     "AccountEntity " + username + " not found");
        }
        return User.builder()
                   .username(account.getEmail())
                   .password(account.getPassword())
                   .roles(account.getRoles()
                                 .stream()
                                 .map(Enum::name)
                                 .toArray(String[]::new))
                   .build();
    }
}
