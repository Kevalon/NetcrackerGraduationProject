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
	    4. Обработать ошибку при удалении товара из каталога (Добавить к продукту поле is_deleted)
	    4.5 Сделать для удаления категории то же, что и для мейкера (запрет удаления при товарах в категории)
	    5. Сделать так, что при добавлении товара менеджер выбирал мейкера по выпадашке, а не вручную по id.
	    6. Добавить выбор категории для товара при добавлении нового товара
	    7. Добавить админам просмотр юзеров (в форме)
	    * Сделать в поиске выпадашку по категориям
	    * Обработка ошибок. Добавить свои вьюшки для разных ошибок
	    * Фильтр заказов для менеджеров
	 */

}
