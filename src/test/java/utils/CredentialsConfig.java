package utils;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:~/w/qa_guru_2021/16/credentials.properties",
        "classpath:credentials.properties"
})
public interface CredentialsConfig extends Config {
    @Key ("login")
    String login();

    @Key("password")
    String password();
}
