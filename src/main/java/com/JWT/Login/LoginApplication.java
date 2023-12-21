package com.JWT.Login;

import com.JWT.Login.dto.AppUser;
import com.JWT.Login.dto.Role;
import com.JWT.Login.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class LoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}



	@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null,"Role_User"));
			userService.saveRole(new Role(null,"Role_Manger"));
			userService.saveRole(new Role(null,"Role_Admin"));
			userService.saveRole(new Role(null,"Role_Super_Admin"));

			userService.saveAppUser(new AppUser(null,"Shubham","shubham123","1234",new ArrayList<>()));
			userService.saveAppUser(new AppUser(null,"Amit","amit123","1234",new ArrayList<>()));
			userService.saveAppUser(new AppUser(null,"Ajay","ajay123","1234",new ArrayList<>()));
			userService.saveAppUser(new AppUser(null,"Rachit","rachit123","1234",new ArrayList<>()));

			userService.addRoleToUser("shubham123","Role_User");
			userService.addRoleToUser("amit123","Role_Manager");
			userService.addRoleToUser("ajay123","Role_Admin");
			userService.addRoleToUser("rachit123","Role_Super_Admin");


		};
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
