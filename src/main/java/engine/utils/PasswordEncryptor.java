package engine.utils;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncryptor {

    PasswordEncoder passwordEncoder =
             PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public PasswordEncryptor() {
    }
}
