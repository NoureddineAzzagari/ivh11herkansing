package sample.web.ui.domain;

import lombok.Getter;

// Singleton for config settings and for other information that only requires one class
@Getter
public final class Configuration {
    private final String version = "0.4";
    private final String name = "Good Spring Boot Application";
    private final int users =  0;

    private Configuration() {
    }

    public static Configuration getConfiguration(){
        return new Configuration();
    }
}