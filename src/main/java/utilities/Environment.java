package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:environmentConfig/${env}.properties"})
public interface Environment extends Config {
    @Key("App.Url")
    String appUrl();

    @Key("App.User")
    String appUsername();

    @Key("App.Pass")
    String appPassword();

    @Key("DB.Host")
    String dbHostname();

    @Key("DB.User")
    String dbUsername();

    @Key("DB.Pass")
    String dbPassword();

    @Key("DB.DRIVER")
    String dbDriver();
}
