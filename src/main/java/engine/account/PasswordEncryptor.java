package engine.account;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {

    private final PasswordEncoder passwordEncoder =
             PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public PasswordEncryptor() {
    }

    public String encrypt(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean matches(String original, String encrypted) {
        return passwordEncoder.matches(original, encrypted);
    }

    public boolean upgradeEncoding(String password) {
        return passwordEncoder.upgradeEncoding(password);
    }
}
