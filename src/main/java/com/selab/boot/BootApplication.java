package com.selab.boot;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@EnableScheduling
@EnableAspectJAutoProxy
@SpringBootApplication
public class BootApplication {

    @Setter(onMethod_ = @Autowired)
    private ApplicationInitializer initializer;

    @PostConstruct
    public void started() throws Exception{
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        initializer.init();
    }

    public static void main(String[] args) {

        SpringApplication.run(BootApplication.class, args);

    }

}
