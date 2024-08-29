package com.generation.progetto_finale;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.progetto_finale.auth.model.Alarm;
import com.generation.progetto_finale.auth.model.UserAdditionalInfo;
import com.generation.progetto_finale.auth.repository.AlarmRepository;
import com.generation.progetto_finale.auth.repository.UserAdditionalInfoRepository;

@SpringBootTest
class ProgettoBaseApplicationTests {

	@Autowired
	UserAdditionalInfoRepository uRepo;
	@Autowired
	AlarmRepository aRepo;

	@Test
	void contextLoads() 
	{
		UserAdditionalInfo user = uRepo.findById(1).get();

		Alarm sveglia = new Alarm();

		sveglia.setTime(LocalTime.of(17,15));
		
		List<String> days = new ArrayList<>();
		days.add("Giovedì");
		sveglia.setDays(days);
		sveglia.setUser(user);
		aRepo.save(sveglia);
	}

}
