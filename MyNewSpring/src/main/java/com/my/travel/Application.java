package com.my.travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.my.travel.dao.UserRepository;
import com.my.travel.model2.User;

//@ComponentScan(basePackages = { "com.creditone.creditone.*" })
/*@EnableJpaRepositories("com.creditone.creditone.*")
@SpringBootApplication(scanBasePackages={
		"com.creditone.creditone", "model"})
@EntityScan("model.*")
*/
@SpringBootApplication
public class Application {
	@Autowired 
	private UserRepository urepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

 /*   
    @Bean
	CommandLineRunner runner() {
		return args -> {
			
			
			urepository.save(new User("user", "$2a$04$1.YhMIgNX/8TkCKGFUONWO1waedKhQ5KrnB30fl0Q01QKqmzLf.Zi", "USER"));
			urepository.save(new User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG", "ADMIN"));
		};
	}	

*/
    
    

}