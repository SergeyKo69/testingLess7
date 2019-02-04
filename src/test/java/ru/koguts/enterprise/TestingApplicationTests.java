package ru.koguts.enterprise;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import ru.koguts.enterprise.model.User;
import ru.koguts.enterprise.service.UserServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TestingApplicationTests {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private TestRestTemplate testRestTemplate;

	private User user = null;

	@Before
	public void initObjects(){
		this.user = new User("Ivanov");
	}

	@After
	public void destroyObjects(){
		if (user != null){
			userService.delete(user);
		}
	}

	@Test
	public void testDb(){

		userService.create(user);
		Assert.assertNotNull(user.getId());
		Assert.assertEquals(user.getName(),"Ivanov");
		user.setName("Petrov");
		userService.update(user);
		Assert.assertEquals(user.getName(),"Petrov");
		Assert.assertEquals(userService.findByName(user.getName()).getName(),user.getName());
		Assert.assertNotNull(userService.findAll());
		userService.delete(user);
		user = null;

	}

	@Test
	public void testUserWebService(){

		userService.create(user);
		Assert.assertNotNull(user.getId());
		ResponseEntity<User> entity = testRestTemplate.getForEntity("/user?name={user}",
				User.class, user.getName());
		Assert.assertTrue(entity.getStatusCode() == HttpStatus.OK);

		User curUser = entity.getBody();
		Assert.assertTrue(curUser.getName().
				equalsIgnoreCase(user.getName()));

		userService.delete(user);
		user = null;
	}

}

