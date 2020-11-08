package engine.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceDB implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceDB(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
             throws UsernameNotFoundException {
        var account = accountRepository.findByEmail(username);
        return User.builder()
                   .username(account.getEmail())
                   .password(account.getPassword())
                   .roles("ADMIN")
                   .build();
        //TODO add roles
    }
}
