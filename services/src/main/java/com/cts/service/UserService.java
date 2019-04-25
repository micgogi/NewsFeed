package com.cts.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.entity.Articles;
import com.cts.entity.KeywordSearch;
import com.cts.entity.Role;
import com.cts.entity.Users;
import com.cts.repository.ArticleRepository;
import com.cts.repository.KeywordRepository;
import com.cts.repository.RoleRepository;
import com.cts.repository.UsersRepository;

@Service
public class UserService implements UserDetailsService {
@Autowired UsersRepository userRepo;
@Autowired RoleRepository roleRepo;
@Autowired ArticleRepository articleRepo;
@Autowired KeywordRepository keywordRepo;
public boolean save(Users user){
	userRepo.save(user);
	return true;
}

public Users getUserByUsername(String username){
	return userRepo.findByUsername(username);
}
public List<Role> getAllRole(){
	return (List<Role>)roleRepo.findAll();
}
public Role getRoleById(int id){
	return roleRepo.findById(id);
}
public Boolean existsByEmail(String email){
	if(userRepo.existsByEmail(email)){
		return true;
	}else{
		return false;
	}
}
public Boolean existsByUsername(String username){
	if(userRepo.existsByUsername(username)){
		return true;
	}else{
		return false;
	}
}

public Users getArticleByUsername(String username){
	return  userRepo.findByUsername(username);
}

public boolean saveArticle(List<Articles> articleList){
	if(articleRepo.saveAll(articleList) != null){
		return true;
	}else{
		return false;
	}
	
}
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
	Users users = userRepo.findByUsername(username);
	if(users==null) {
		throw new RuntimeException("No User Found");
	}
	if(!users.isStatus())
		throw new RuntimeException("You are Blocked by admin");
		
	
	
	Role role = roleRepo.findById(users.getRole());
	List<GrantedAuthority> authorties = new ArrayList<GrantedAuthority>();
	authorties.add(new SimpleGrantedAuthority(role.getRoleName()));
	User user1 = new User(users.getUsername(),users.getPassword(),authorties);
	return user1;
	
	
}

public List<KeywordSearch> getAllSearchWordsByUsername(String username){
	return keywordRepo.findKeywordByUsername(username);
}

public boolean saveSearchword(KeywordSearch keyword){
	keywordRepo.save(keyword);
	return true;
}

public boolean deleteKeyword(int id){
	keywordRepo.deleteById(id);
	return true;
}
public List<Users> getAllUsers(){
	List<Users> usersList= (List<Users>) userRepo.findAll();
	List<Users> usersWithActiveStatus = new ArrayList<Users>();
	for(Users u:usersList){
		if(u.isStatus()){
			usersWithActiveStatus.add(u);
		}
	}
	  return usersWithActiveStatus;
}



public Boolean blockUserById(Integer id) {
// TODO Auto-generated method stub
	Users users = userRepo.findById(id).get();
	users.setStatus(false);
	userRepo.save(users);
	return true;
}
}
