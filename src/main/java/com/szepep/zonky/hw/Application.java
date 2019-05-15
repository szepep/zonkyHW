package com.szepep.zonky.hw;

import com.szepep.zonky.hw.api.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private static Logger log = LoggerFactory
            .getLogger(Application.class);
    private final ScheduledExecutorService executorService;
    private final int period;
    private final TimeUnit timeUnit;
    private final Job job;

    @Autowired
    public Application(ScheduledExecutorService executorService,
                       Job job,
                       @Value("${period.time.value}") int period,
                       @Value("${period.time.unit}") TimeUnit unit) {
        this.executorService = executorService;
        this.job = job;
        this.period = period;
        this.timeUnit = unit;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("Application started with arguments: " + String.join(", ", args));
        executorService.scheduleAtFixedRate(job, 0, period, timeUnit);

    }
}
