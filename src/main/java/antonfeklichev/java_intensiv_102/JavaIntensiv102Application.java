package antonfeklichev.java_intensiv_102;

import antonfeklichev.java_intensiv_102.service.ExceptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class JavaIntensiv102Application {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(JavaIntensiv102Application.class, args);

        ExceptionService exceptionService = context.getBean(ExceptionService.class);

        exceptionService.methodThatCatchesException();
    }

}
