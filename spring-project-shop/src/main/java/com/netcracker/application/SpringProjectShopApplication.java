package com.netcracker.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectShopApplication.class, args);
	}

	// Когда добавляешь или редактишь категорию, то не выводятся повторно категории (но мейкеры выводятся).
	// при добавлении нового товара проверить, что везде проверяется, что unique поля по новому не создаются
}
