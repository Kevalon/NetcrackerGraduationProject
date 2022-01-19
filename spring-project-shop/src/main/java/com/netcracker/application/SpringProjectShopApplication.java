package com.netcracker.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringProjectShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringProjectShopApplication.class, args);
	}

	/*
	TODO:
		2. Добавить поиск/фильтрацию товара
		3. Добавить возможность покупать товар пользователям
	    4. Обработка ошибок. Добавить свои вьюшки для разных ошибок
	    5. Добавить в профиле возможность редактировать данные
	    6. Разобраться и воплотить валидацию
	 */

}
