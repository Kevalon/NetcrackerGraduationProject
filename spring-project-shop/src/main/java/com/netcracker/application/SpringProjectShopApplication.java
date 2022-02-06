package com.netcracker.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectShopApplication.class, args);
	}

	// Логин нужно добавить только при взаимодействии с корзиной
	// Убрать указание товара в магазине, где это не нужно
	// Выводить какая именно ошибка при добавлении
	// Сделать табличка у админа
}
