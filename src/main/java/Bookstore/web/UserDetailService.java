package Bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Bookstore.domain.User;
import Bookstore.domain.UserRepository;

@Service
public class UserDetailService implements UserDetailsService {
	private final UserRepository urepository;
	
	@Autowired
	public UserDetailService(UserRepository userRepository) {
		this.urepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currentuser = urepository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currentuser.getPassword(), 
        		AuthorityUtils.createAuthorityList(currentuser.getRole()));
        return user;
	}
	
}
