package com.yellowdogprod.rootsandbones.user;

import java.security.cert.PKIXRevocationChecker.Option;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.yellowdogprod.rootsandbones.ResponseBean;
import com.yellowdogprod.rootsandbones.profile.Profile;
import com.yellowdogprod.rootsandbones.profile.ProfileService;
import com.yellowdogprod.rootsandbones.registration.token.ConfirmationToken;
import com.yellowdogprod.rootsandbones.registration.token.ConfirmationTokenService;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	private final static String USER_NOT_FOUND_ERROR = "User with username %s not found.";
	private final static String WRONG_USERNAME = "Wrong username.";
	private final static String WRONG_PASSWORD = "Wrong password.";
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ConfirmationTokenService confirmationTokenService;

	@Autowired
	private ProfileService profileService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(
					() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERROR, username))
				);
	}
	
	
	public String signUpUser(User user) {
		Boolean userExists = userRepository.findByUsername(user.getUsername()).isPresent();
		if(userExists) {
			throw new IllegalStateException("Username already taken.");
		}
		
		Boolean emailExists = userRepository.findByEmail(user.getEmail()).isPresent();
		if(emailExists) {
			throw new IllegalStateException("Email already taken.");
		}
		
		
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		
		user = userRepository.save(user);
		
		profileService.createNewProfile(user);
		
		//TODO: Send confirmation token 
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(
				token, LocalDateTime.now(), LocalDateTime.now().plusDays(1), user
				);
		
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		return token;
	}
	
    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
    
    public ResponseBean<User> login(String username, String password) {
    	LOGGER.info(username+" is logging in.");
    	String encodedPassword = bCryptPasswordEncoder.encode(password);
    	Optional<User> user = userRepository.findByUsername(username);
    	if(!user.isPresent()) {
    		LOGGER.error(WRONG_USERNAME);
    		return ResponseBean.error(WRONG_USERNAME);
    	}
    	if(!bCryptPasswordEncoder.matches(password, user.get().getPassword())) {
    		LOGGER.error(WRONG_PASSWORD);
    		return ResponseBean.error(WRONG_PASSWORD);
    	}
    	user.get().setProfile(profileService.getProfileByUserId(user.get().getId()));
    	return new ResponseBean<User>(user.get());
    }


	public User getUserById(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) return user.get();
		return null;
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}

}
