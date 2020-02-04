package com.accountmicroservice;

import com.accountmicroservice.models.Account;
import com.accountmicroservice.models.Operation;
import com.accountmicroservice.models.User;
import com.accountmicroservice.repo.AccountRepo;
import com.accountmicroservice.repo.OperationRepo;
import com.accountmicroservice.repo.UserRepo;
import com.accountmicroservice.utils.EncryptionUtils;
import org.jasypt.util.text.BasicTextEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
		log.info("Hello world!");

		userRepo.save(new User(1, "user1@example.com", encryptionUtils.encrypt("123456"), "user"));
		userRepo.save(new User(2, "user2@example.com", encryptionUtils.encrypt("789123"), "user"));
		userRepo.save(new User(3, "user3@example.com", encryptionUtils.encrypt("345123"), "user"));

		accountRepo.save(new Account(1, 1, 1000.00));
		accountRepo.save(new Account(2, 2, 1500.00));
		accountRepo.save(new Account(3, 3, 3000.00));

		operationRepo.save(new Operation(1, Calendar.getInstance().getTime(), 30.00, "sending 30", 1, 2));
		operationRepo.save(new Operation(2, Calendar.getInstance().getTime(), 3.00, "sending 3", 2, 1));
		operationRepo.save(new Operation(3, Calendar.getInstance().getTime(), 40.00, "sending 40", 2, 3));
		operationRepo.save(new Operation(4, Calendar.getInstance().getTime(), 100.00, "sending 100", 3, 1));
		operationRepo.save(new Operation(5, Calendar.getInstance().getTime(), 99.00, "sending 99", 1, 3));
	}

	@Bean
	public BasicTextEncryptor textEncryptor() {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("mySecretEncriptionKeyBlaBla1234");
		return textEncryptor;
	}
}
