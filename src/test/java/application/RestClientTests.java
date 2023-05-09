package application;

import application.models.User;
import application.service.UserRestClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RestClientTests {
	private static final String baseUrl = "http://localhost:1234/api";
	private WebClient webClient = WebClient.create(baseUrl);
	application.service.UserRestClient UserRestClient = new UserRestClient(webClient);

	@Test
	void getAllUsers() {
		List<User> userList = UserRestClient.retrieveAllUsers();
		System.out.println( "UserList: " + userList);
		assertTrue(userList.size() > 0);
	}

	@Test
	void getUsersById() {
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

}
