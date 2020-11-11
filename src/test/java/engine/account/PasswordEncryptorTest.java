package engine.account;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class PasswordEncryptorTest {

    private final String password = "q1w2e3r4";
    private final PasswordEncryptor passwordEncryptor = new PasswordEncryptor();

    @Test
    void matches_returns_true_when_given_original_password_and_encrypted_one() {
        final String encrypted = passwordEncryptor.encrypt(password);

        assertTrue(passwordEncryptor.matches(password, encrypted));
    }

    @Test
    void finds_correct_encrypting_algorithm_based_on_id() {
        final String encrypted =
                 "{bcrypt}$2a$10$myjE6dBl.0K.v5/Z9qKBceZdl/kyo6sExj/8Yu0eU79KKgrdBlUre";
        assertTrue(passwordEncryptor.matches(password, encrypted));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asfa", "{}asfasf", "{asfda}asfa"})
    void throws_exception_when_null_or_undefined_algorithm_id(String encrypted) {
        assertThrows(Exception.class,
                     () -> passwordEncryptor.matches(password, encrypted));
    }

    @Test
    void adds_algorithm_id_before_string() {
        var encrypted = passwordEncryptor.encrypt(password);

        assertTrue(encrypted.matches("^\\{\\w+}.*$"));
    }

    @Test
    void upgrade_encoding_returns_true_if_password_uses_non_default_algorithm() {
        final String oldPassword = new Pbkdf2PasswordEncoder().encode(password);

        assertTrue(passwordEncryptor.upgradeEncoding(oldPassword));
    }
}