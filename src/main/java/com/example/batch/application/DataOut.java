package com.example.batch.application;

import com.example.batch.data.serviceImpl.TransferDataServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import java.util.Date;

/**
 *
 * @author kunanan.t
 */

@Slf4j
@SpringBootApplication
@ComponentScan(basePackages = {"com.example.batch.data.*"})
public class DataOut implements CommandLineRunner {

    @Autowired
    private Environment env;
    @Autowired
    private TransferDataServiceImpl transferDataServiceImpl;
    public static void main(String[] args) {
        SpringApplication.run(DataOut.class, args);
    }

    public void run(String... args) {
        String endLine = env.getProperty("end.line");
        log.info("Version {} ", env.getProperty("initial.version"));
        log.info("Env: {} ", env.getProperty("env.key"));
        log.info("Active profile property: {}", env.getProperty("spring.profile.active"));
        log.info(endLine);
        log.info("         Batch Export Data START : {}", new Date());
        log.info(endLine);

        try {

            log.info("******************* STEP 1 Batch INSERT To TABLE *****************");
            log.info(endLine);

            transferDataServiceImpl.transfer();

            log.info(endLine);

        } catch (Exception e) {
            log.error(e.toString());
        }

    }
}
