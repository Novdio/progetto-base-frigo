package com.generation.progetto_finale;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.progetto_finale.notification.NotificationService;

@SpringBootTest
class ProgettoBaseApplicationTests {

	@Autowired
	NotificationService nServ;
	@Test
	void contextLoads() {
		nServ.notify("Eccomi qui cavolaccio");
	}

}
