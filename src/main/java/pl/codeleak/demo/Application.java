package pl.codeleak.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

@SpringBootApplication
@Controller
public class Application {

    public static String UPLOAD_DIRECTORY = "upload-dir";

    @RequestMapping({"", "/"})
    public String index() {
        return "index";
    }

    @Bean
    CommandLineRunner init() {
        return (String[] args) -> new File(UPLOAD_DIRECTORY).mkdir();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
