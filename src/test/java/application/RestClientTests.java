package application;

import static application.constants.UserEndPoints.BASE_URL;
import application.models.User;
import application.service.UserRestClient;

import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClientException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
class RestClientTests {

	public WebClient webClient = WebClient.create(BASE_URL);
	UserRestClient UserRestClient = new UserRestClient(webClient);

	@Test
	void getAllUsers(){
		List<User> userList = UserRestClient.retrieveAllUsers();
		System.out.println( "UserList: " + userList);
		assertTrue(userList.size() > 0);
	}

	@Test
	void getUsersById(){
		LocalDate birthdate = LocalDate.of(2023, 05, 9);
		User testUser = new User(5L,"Mustermann","Max",birthdate,"max.mustermann@email.com");
		User createdUser = UserRestClient.addNewUser(testUser);
		User user = UserRestClient.retrieveById(createdUser.getId());
		System.out.println( "User: " + user);
		assertEquals("Max", user.getFirstName());
	}

	@Test
	void addNewUser(){
		LocalDate birthdate = LocalDate.of(2023, 05, 9);
		User testUser = new User(5L,"Mustermann","Max",birthdate,"max.mustermann@email.com");
		User createdUser = UserRestClient.addNewUser(testUser);
	}

	@Test
	void deleteUser(){
		LocalDate birthdate = LocalDate.of(2023, 05, 9);
		User testUser = new User(5L,"Max","Mustermann",birthdate,"max.mustermann@email.com");
		User createdUser = UserRestClient.addNewUser(testUser);
		System.out.println("User created: " + createdUser);
		Boolean success = UserRestClient.deleteUser(createdUser.getId());
		assertTrue(success, "User create and delete was not sucessfull");
	}
	@Test
	void deleteUser_NotFound(){
		long userId = 10l;
		Assertions.assertThrows(WebClientException.class, ()-> UserRestClient.deleteUser(userId));
	}

}
