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
	    6. Добавить админам просмотр юзеров (в форме)
	    7. Сделать так, что при добавлении товара менеджер выбирал мейкера по выпадашке, а не вручную по id.
	    * Сделать в поиске выпадашку по категориям (добавить выпадашки там, где возможно)Fix
	    * Фильтр заказов для менеджеров
	 */

}
