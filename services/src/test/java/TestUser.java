import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.entity.Articles;
import com.cts.entity.KeywordSearch;
import com.cts.entity.Users;
import com.cts.repository.ArticleRepository;
import com.cts.repository.KeywordRepository;
import com.cts.repository.UsersRepository;
import com.cts.service.UserService;

public class TestUser {

	
	@InjectMocks
	UserService userService;
	@Mock 
	UsersRepository userRepo;
	@Mock
	ArticleRepository articleRepo;
	@Mock
	KeywordRepository keywordRepo;
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testUser(){
		Users user = new Users();
		user.setUsername("rahul");
		user.setEmail("rahul.gogyani@gmail.com");
		user.setPassword("12345678");
		user.setRole(2);
		userService.save(user);
		verify(userRepo,times(1)).save(user);
		
	}
	
	@Test
	public void testUserByUsername(){
		Users user = new Users();
		user.setUsername("rahul");
		user.setEmail("rahul.gogyani@gmail.com");
		user.setPassword("123456789");
		user.setRole(2);
		Users user1= userService.getUserByUsername(user.getUsername());
		when(userRepo.save(user)).thenReturn(new Users("rahul","rahul.gogyani@gmail.com","123456789",2, false));
		assertEquals(user.getUsername(),"rahul");
		assertEquals(user.getPassword(),"123456789");
		assertEquals(user.getRole(),2);
		assertEquals(user.getEmail(),"rahul.gogyani@gmail.com");
		assertEquals(user.isStatus(),false);
		
	}
	@Test
	public void testExistByEmail(){
		Users user = new Users();
		user.setUsername("rahul");
		user.setEmail("rahul.gogyani@gmail.com");
		user.setPassword("123456789");
		user.setRole(2);
		when(userService.existsByEmail("rahul.gogyani@gmail.com")).thenReturn(true);
		boolean check = userService.existsByEmail(user.getEmail());
	
		assertEquals(true,check);
	}
	
	@Test
	public void testExistByUsername(){
		Users user = new Users();
		user.setUsername("rahul");
		user.setEmail("rahul.gogyani@gmail.com");
		user.setPassword("123456789");
		user.setRole(2);
		when(userService.existsByUsername("rahul")).thenReturn(true);
		boolean check = userService.existsByUsername(user.getUsername());
		
		assertEquals(true,check);
	}
	
	@Test
	public void saveArticles(){
	  List<Articles> list = new ArrayList<Articles>();
	  
	  list.add(new Articles("ABCDEF","BCDERH","HGFBNB","HHJKLK","JHGHGGF"));
	  userService.saveArticle(list);
	  verify(articleRepo,times(1)).saveAll(list);
	}
	
	
	@Test
	public void testSearchKeywordByUsername(){
	List<KeywordSearch> keywordList = new ArrayList<KeywordSearch>();
	keywordList.add(new KeywordSearch("rahul","mahi"));
	keywordList.add(new KeywordSearch("rahul","mahi2"));
	when(keywordRepo.findKeywordByUsername("rahul")).thenReturn(keywordList);
	List<KeywordSearch>  keywordList1=userService.getAllSearchWordsByUsername("rahul");
	assertEquals(keywordList.size(),keywordList1.size());

	}
	
	@Test
	public void testDeleteKeyword(){
		userService.deleteKeyword(1);
		verify(keywordRepo,times(1)).deleteById(1);
	}
	
	@Test
	public void saveKeyword(){
		KeywordSearch keyword = new KeywordSearch("rahul","mahi");
		userService.saveSearchword(keyword);
		verify(keywordRepo,times(1)).save(keyword);
		
	}
	
	@Test
	public void blockUser(){                       
		Users users = new Users();
		users.setUsername("rahul");
		users.setEmail("rahul.gogyani@gmail.com");
		users.setPassword("123456789");
		users.setRole(2);
		users.setStatus(true);;
		when(userRepo.findById(1)).thenReturn(Optional.of(users));
		assertEquals(true,userService.blockUserById(1));
	}
	
	
	
	
}
