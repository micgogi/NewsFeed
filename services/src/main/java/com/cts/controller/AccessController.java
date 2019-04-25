package com.cts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.entity.Articles;
import com.cts.entity.KeywordSearch;
import com.cts.entity.Users;
import com.cts.exception.ExeceptionHandle;
import com.cts.repository.ArticleRepository;
import com.cts.service.UserService;

@CrossOrigin(origins="http://localhost:4200")
@Controller
@RequestMapping("api/user")
public class AccessController extends ExeceptionHandle{
@Autowired UserService userService;
@Autowired ArticleRepository articleRepo;
	@GetMapping("/api/test/user/saveArticle")
	@PreAuthorize("hasRole('ROLE_USER') ")
	public boolean addToBookmarks(Users users){
		Users user1 =userService.getUserByUsername(users.getUsername());
		System.out.println(user1);
		user1.getArticleList().add(users.getArticleList().get(0));
		userService.saveArticle(users.getArticleList());
		return true;
		
	}
	@GetMapping("/api/test/user/getArticles")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_GUEST')")
	public List<Articles> getAllArticlesByUsers(Users users ){
			Users userArticle = userService.getArticleByUsername(users.getUsername());
			return userArticle.getArticleList();
	}
	

	@PostMapping("saveKeyword")
   @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_GUEST')")
	public ResponseEntity<?> saveSearchedWord(@RequestBody KeywordSearch keywordSearch){
		return  new ResponseEntity<Boolean>(userService.saveSearchword(keywordSearch),HttpStatus.OK);
	}
	@GetMapping("getAllSearchKeywords/{username}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_GUEST')")
	public ResponseEntity<List<KeywordSearch>> getAllKeywords(@PathVariable String username){
		return new ResponseEntity<List<KeywordSearch>>(userService.getAllSearchWordsByUsername(username)
				,HttpStatus.OK);
	}
	
	@DeleteMapping("deleteSearchKeyword/{searchKeywordId}")
	public ResponseEntity<Boolean> deleteSearchKeyword(@PathVariable Integer searchKeywordId){
		System.out.println("IN delete");
		return new ResponseEntity<Boolean>(userService.deleteKeyword(searchKeywordId),HttpStatus.OK);
	}
	
	@GetMapping("getAllUsers")
		@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_GUEST')")
	public ResponseEntity<List<Users>> getAllUsers(){
	
		return new ResponseEntity<List<Users>>(userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("blockUserById/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_GUEST')")
	public ResponseEntity<Boolean> blockUser(@PathVariable Integer id){
	
	 return new ResponseEntity<Boolean>(userService.blockUserById(id),HttpStatus.OK);
	}
	@GetMapping("searchUser/{username}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN') or hasRole('ROLE_GUEST')")
	public ResponseEntity<Users> searchUser(@PathVariable String username){
	
		 return new ResponseEntity<Users>(userService.getUserByUsername(username),HttpStatus.OK);
		}
}
