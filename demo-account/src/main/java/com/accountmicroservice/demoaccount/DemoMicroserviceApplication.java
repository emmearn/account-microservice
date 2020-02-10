package com.accountmicroservice.demoaccount;

import com.accountmicroservice.demoaccount.models.Account;
import com.accountmicroservice.demoaccount.models.Operation;
import com.accountmicroservice.demoaccount.models.User;
import com.accountmicroservice.demoaccount.repo.AccountRepo;
import com.accountmicroservice.demoaccount.repo.OperationRepo;
import com.accountmicroservice.demoaccount.repo.UserRepo;
import com.accountmicroservice.demoaccount.utils.EncryptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;

@SpringBootApplication
public class DemoMicroserviceApplication implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	@Autowired
	AccountRepo accountRepo;

	@Autowired
	OperationRepo operationRepo;

	@Autowired
	EncryptionUtils encryptionUtils;

	private static final Logger log = LoggerFactory.getLogger(DemoMicroserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoMicroserviceApplication.class, args);
	}

	@Override
	public void run(String... string) throws Exception {
		log.info("Application run..");

		userRepo.save(new User("U53R1", "user1@example.com", encryptionUtils.encrypt("123456"), "user"));
		userRepo.save(new User("U53R2", "user2@example.com", encryptionUtils.encrypt("789123"), "user"));
		userRepo.save(new User("U53R3", "user3@example.com", encryptionUtils.encrypt("345123"), "user"));

		accountRepo.save(new Account("4CC0UN71", "U53R1", 1000.00));
		accountRepo.save(new Account("4CC0UN72", "U53R2", 1500.00));
		accountRepo.save(new Account("4CC0UN73", "U53R3", 3000.00));

		operationRepo.save(new Operation("0P3R4710N1", Calendar.getInstance().getTime(), 30.00, "sending 30", "4CC0UN71", "4CC0UN72"));
		operationRepo.save(new Operation("0P3R4710N2", Calendar.getInstance().getTime(), 3.00, "sending 3", "4CC0UN72", "4CC0UN71"));
		operationRepo.save(new Operation("0P3R4710N3", Calendar.getInstance().getTime(), 40.00, "sending 40", "4CC0UN72", "4CC0UN73"));
		operationRepo.save(new Operation("0P3R4710N4", Calendar.getInstance().getTime(), 100.00, "sending 100", "4CC0UN73", "4CC0UN71"));
		operationRepo.save(new Operation("0P3R4710N5", Calendar.getInstance().getTime(), 99.00, "sending 99", "4CC0UN71", "4CC0UN73"));

		log.info("Database filled");
	}
}
