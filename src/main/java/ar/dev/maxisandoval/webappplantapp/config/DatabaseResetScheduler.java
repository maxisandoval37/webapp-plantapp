package ar.dev.maxisandoval.webappplantapp.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@Component
public class DatabaseResetScheduler {

    private final ApplicationContext applicationContext;

    public DatabaseResetScheduler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Scheduled(fixedRate = 3600000) // 3600000 ms = 1 hora
    public void resetDatabase() {
        ((ConfigurableApplicationContext) applicationContext).close();
        System.exit(0);
    }
}