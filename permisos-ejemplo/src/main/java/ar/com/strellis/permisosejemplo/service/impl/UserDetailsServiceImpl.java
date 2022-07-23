package ar.com.strellis.permisosejemplo.service.impl;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ar.com.strellis.permisosejemplo.dao.UserDAO;
import ar.com.strellis.permisosejemplo.model.User;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserDAO userRepository;

    //required by cglib because I use class based aspect weaving
    protected UserDetailsServiceImpl() {
    }

    public UserDetailsServiceImpl(UserDAO userRepository) {
    	log.trace("Estoy en UserDetailsServieImpl.constructor");
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
    	log.trace("Estoy en AuthenticationUserDetailsGetter.loadUserByUsername");
        User user = userRepository.findByLogin(username);
        throwExceptionIfNotFound(user, username);
        return new AuthenticationUserDetails(user);
    }

    private void throwExceptionIfNotFound(User user, String username) {
    	log.trace("Estoy en throwExceptionIfNotFound");
    	if (user == null) {
    		throw new UsernameNotFoundException("User with login " + username + "  has not been found.");
    	}
    }
}