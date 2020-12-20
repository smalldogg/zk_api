package com.my.zk.zk_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication public class ZkApiApplication {

		public static void main( String[] args ) throws IOException, InterruptedException {

				ConfigurableApplicationContext ctx = SpringApplication.run( ZkApiApplication.class, args );

		}

}
