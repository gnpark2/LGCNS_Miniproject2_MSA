// package routine.routine_service;

// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication
// public class RoutineServiceApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(RoutineServiceApplication.class, args);
// 	}

// }

package routine.routine_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "routine")
@EntityScan(basePackages = "routine.entity")
@EnableJpaRepositories(basePackages = "routine.repo")
public class RoutineServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoutineServiceApplication.class, args);
    }
}
