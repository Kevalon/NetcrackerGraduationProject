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
	    5. Добавление товара
	    5.1 При добавлении товара указывается maker, а не его id.
	    5.2 При добавлении товара указывается его категория (подумать про несколько)
	    6. Добавить админам просмотр юзеров (в форме)
	    7. Сделать так, что при добавлении товара менеджер выбирал мейкера по выпадашке, а не вручную по id.
	    * Сделать в поиске выпадашку по категориям
	    * Обработка ошибок. Добавить свои вьюшки для разных ошибок
	    * Фильтр заказов для менеджеров
	 */

}
