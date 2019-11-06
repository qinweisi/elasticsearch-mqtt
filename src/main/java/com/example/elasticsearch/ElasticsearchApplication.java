package com.example.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticsearchApplication {

	public static void main(String[] args) {
//		/**
//		 * SpringBoot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
//		 * 解决netty冲突后初始化client时还会抛出异常
//		 * java.lang.IllegalStateException: availableProcessors is already set to [12], rejecting [12]
//		 */
//		System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(ElasticsearchApplication.class, args);
	}

}
