import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.App;
import com.cts.entity.KeywordSearch;
import com.cts.entity.ResponseMessage;
import com.cts.entity.Users;
import com.mysql.fabric.Response;



@RunWith(SpringRunner.class)
@SpringBootTest(classes=App.class,webEnvironment=WebEnvironment.RANDOM_PORT)
public class TestController {
//	private static final Logger logger = (Logger) LoggerFactory.logger(TestController.class);
     
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	@Autowired
	TestRestTemplate testRestTemplate;
	
	
	
	@Test
	public void testSuccessForSignUp() throws Exception{
		Users user = new Users();
		user.setUsername("qwerty");
		user.setEmail("qwerty@gmail.com");
		user.setPassword("123456789");
		user.setRole(2);
		ResponseEntity<ResponseMessage> response= testRestTemplate.postForEntity("/api/auth/signup",
				user,ResponseMessage.class );
		assertThat(response.getBody().getMessage(),containsString("Message: Details saved Successfully"));
	}
	
	@Test
	public void testForUsernameAlreadyTaken() throws Exception{
		Users user = new Users();
		user.setUsername("abcffgghghfed");
		user.setEmail("abcghgfhjgfggf@gmail.com");
		user.setPassword("123456789");
		user.setRole(2);
		ResponseEntity<ResponseMessage> response= testRestTemplate.postForEntity("/api/auth/signup",
				user,ResponseMessage.class );
		assertThat(response.getBody().getMessage(),containsString("Fail -> Username is already taken!"));
	}
	
	@Test
	public void testForEmailAlreadyTaken() throws Exception{
		Users user = new Users();
		user.setUsername("abcffgghghfgfggffed");
		user.setEmail("abcghgfhjgfggf@gmail.com");
		user.setPassword("123456789");
		user.setRole(2);
		ResponseEntity<ResponseMessage> response= testRestTemplate.postForEntity("/api/auth/signup",
				user,ResponseMessage.class );
		assertThat(response.getBody().getMessage(),containsString("Fail -> Email is already in use!"));
	}
	
	@Test
	public void testForSuccessfulLogin() throws Exception{
		Users user =new Users();
		user.setUsername("qwerty");
		user.setPassword("123456789");
		ResponseEntity<ResponseMessage> response= testRestTemplate.postForEntity("/api/auth/signin",
				user,ResponseMessage.class );
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	@Test
	public void testForUnsuccessFulLogin() 
		{
		Users user = new Users();
		user.setUsername("vnfvnnbnjn");
		user.setPassword("12345679");
		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signin"
				, user, ResponseMessage.class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.BAD_REQUEST));
			}
	
	@Test
	public void testForEmptyUsername() throws Exception{
		Users user = new Users();
		user.setUsername("");
		user.setPassword("123456789");

		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signup"
				, user, ResponseMessage.class);
		assertThat(response.getBody().getMessage(),equalTo(HttpStatus.BAD_REQUEST));
	}
	
	@Test
	public void testForMinPassowrdLenght() throws Exception{
		Users user = new Users();
		user.setUsername("qwertyuiop");
		user.setPassword("12");
	
		user.setEmail("qwertyuio@gmail.com");
		user.setRole(2);
		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signup"
				, user, ResponseMessage.class);
		assertThat(response.getBody().getMessage(),containsString("error:password password should"
				+ " be minimum 6 character,"));
		
	}
	
	
	
	@Test
	public void testForMaximumUsernameCharacters (){
		Users user = new Users();
		user.setUsername("qwertyuiopasdfghjklzxcvbn");
		user.setPassword("123456789");
		user.setEmail("qwertyuio@gmail.com");
		user.setRole(2);
		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signup"
				, user, ResponseMessage.class);
		assertThat(response.getBody().getMessage(),containsString("error:username username should have"
				+ " atleast 2 character and maximum 20 characters"));
	}
	@Test
	public void testForEmailFieldBlank() throws Exception{
		Users user = new Users();
		user.setUsername("qwertyuiop");
		user.setPassword("123456789");
	
		user.setEmail("");
		user.setRole(2);
		ResponseEntity<ResponseMessage> response = testRestTemplate.postForEntity("/api/auth/signup"
				, user, ResponseMessage.class);
		assertThat(response.getBody().getMessage(),containsString("error:email must not be blank,"));
	}
	

	
//	@Test
//	public void testForAuthentication() throws Exception{
//		String token = "";
//		HttpHeaders header = new HttpHeaders();
//		header.setContentType(MediaType.APPLICATION_JSON);
//		header.set("Authorization", "Bearer "+token);
//		
//	}
	@Test
	public void testSaveSearch(){
		KeywordSearch keywordSearch=new KeywordSearch("rahul","mahi56");
		String accesToken="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eGN2Ym5tMiIsImlhdCI6MTU1MDU1MTQwMCwiZXhwIjoxNTUwNjM3ODAwfQ."
				+ "vHYjNykuyCOxOtsvF1kEAFA-E8dZxR84MVh4_6Czb-kBQGmknfuIHJhpc4uK36Rfxu3jD2058BsoZrbJ9sL73w";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+accesToken);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(keywordSearch,headers);
		ResponseEntity<Boolean> response = testRestTemplate.exchange("/api/user/saveKeyword",HttpMethod.POST,
				httpEntity,Boolean.class);
		LOGGER.info("Response->{}",response.getBody());
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
		assertThat(response.getBody(),equalTo(true));	
	}
	
	@Test
	public void testGetAllSearchKeywordsByUsername(){
		
		String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eGN2Ym5tMiIsImlhdCI6MTU1MDU1MTQwMCwiZXhwIjoxNTUwNjM3ODAwfQ."
				+ "vHYjNykuyCOxOtsvF1kEAFA-E8dZxR84MVh4_6Czb-kBQGmknfuIHJhpc4uK36Rfxu3jD2058BsoZrbJ9sL73w";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(null,headers);
		ResponseEntity<Object[]> response = testRestTemplate.exchange("/api/user/getAllSearchKeywords/{username}",
				HttpMethod.GET, httpEntity, Object[].class, "rahul@gmail.com");
		List<Object> list = Arrays.asList(response.getBody());
		LOGGER.info("Response->{}",list);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));

		
	}
	
	@Test
	public void deleteSearchKeywordById(){
		String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eGN2Ym5tMiIsImlhdCI6MTU1MDU1MTQwMCwiZXhwIjoxNTUwNjM3ODAwfQ."
				+ "vHYjNykuyCOxOtsvF1kEAFA-E8dZxR84MVh4_6Czb-kBQGmknfuIHJhpc4uK36Rfxu3jD2058BsoZrbJ9sL73w";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization","Bearer "+token);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(null,headers);
		ResponseEntity<?> response= testRestTemplate.exchange("/api/user/deleteSearchKeyword/{searchKeywordId}",
				HttpMethod.DELETE,httpEntity,Object.class,"9");
		assertThat(response.getBody(),equalTo(true));
	}
	@Test
	public void testSaveSearchWithNull(){
		String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eGN2Ym5tMiIsImlhdCI6MTU1MDU1MTQwMCwiZXhwIjoxNTUwNjM3ODAwfQ."
				+ "vHYjNykuyCOxOtsvF1kEAFA-E8dZxR84MVh4_6Czb-kBQGmknfuIHJhpc4uK36Rfxu3jD2058BsoZrbJ9sL73w";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(null,headers);
		ResponseEntity<?> response = testRestTemplate.exchange("/api/user/saveKeyword",
				HttpMethod.POST,httpEntity,Object.class);
		LOGGER.info(""+response.getBody());
		assertThat(response.getBody(),equalTo(false));
	}
	@Test
	public void testGetAllUsers(){
		String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eGN2Ym5tMiIsImlhdCI6MTU1MDU1MTQwMCwiZXhwIjoxNTUwNjM3ODAwfQ."
				+ "vHYjNykuyCOxOtsvF1kEAFA-E8dZxR84MVh4_6Czb-kBQGmknfuIHJhpc4uK36Rfxu3jD2058BsoZrbJ9sL73w";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		HttpEntity<?> httpEntity = new HttpEntity<Object>(null,headers);
		ResponseEntity<Object[]> response = testRestTemplate.exchange("/api/user/getAllUsers",
				HttpMethod.GET,httpEntity,Object[].class);
		assertThat(response.getStatusCode(),equalTo(HttpStatus.OK));
	}
	
	@Test
	public void testGetAllUsersByUsername(){
		String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ6eGN2Ym5tMiIsImlhdCI6MTU1MDU1MTQwMCwiZXhwIjoxNTUwNjM3ODAwfQ."
				+ "vHYjNykuyCOxOtsvF1kEAFA-E8dZxR84MVh4_6Czb-kBQGmknfuIHJhpc4uK36Rfxu3jD2058BsoZrbJ9sL73w";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorizaton", "Beares "+token);
		
	}
}
