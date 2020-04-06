package com.ssm.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ssm.pojo.GameType;
import com.ssm.service.GameTypeService;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		System.out.println(applicationContext);
		GameTypeService g = applicationContext.getBean(GameTypeService.class);
		List<GameType> list=g.getAllGameTypes(null,null);
		for (GameType gameType : list) {
			System.out.println(gameType);
		}
//		Date d=new Date();
//		System.out.println(d);
	}
}
