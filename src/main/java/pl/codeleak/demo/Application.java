package pl.codeleak.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
public class Application {

    @RequestMapping({"", "/"})
    public String index() {
        return "index";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
