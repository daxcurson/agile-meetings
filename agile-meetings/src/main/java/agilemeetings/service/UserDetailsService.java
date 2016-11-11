package agilemeetings.service;


import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

import agilemeetings.model.User;

public interface UserDetailsService {
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException, DataAccessException;
	public void save(User user);
	public User getById(long id);
}