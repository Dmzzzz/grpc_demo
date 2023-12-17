package com.grpc.example.grpcDemo;

import com.grpc.example.grpcDemo.advice.ActorRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class GrpcDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(GrpcDemoApplication.class, args);


		ActorRepository actorRepository = (ActorRepository)applicationContext.getBean("actorRepository");

		actorRepository.add("Actor1");

		String str = "str";
	}

}
