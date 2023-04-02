package demo.shopping;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "demo.shopping")
public class AppSettings {
    private String LogoPath = "../bin/logos";

    public String getLogoPath() {
        return LogoPath;
    }

    public void setLogoPath(String logoPath) {
        LogoPath = logoPath;
    }
}
