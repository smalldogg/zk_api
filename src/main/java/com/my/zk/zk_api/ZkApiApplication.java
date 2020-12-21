package com.my.zk.zk_api;

import com.my.zk.zk_api.curator.DateNode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication public class ZkApiApplication {

		public static void main( String[] args ) throws Exception {

				SpringApplication.run( ZkApiApplication.class, args );
		}

}
