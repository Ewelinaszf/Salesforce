package rough;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    public static void main(String[] args) throws IOException {

        Properties config = new Properties();
        Properties OR = new Properties();
        Properties log4j = new Properties();

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
        config.load(fis);

        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
        OR.load(fis);

        fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");
        log4j.load(fis);
        System.out.println(config.getProperty("browser"));
        System.out.println(OR.getProperty("username"));
    }
}

