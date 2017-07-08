package agilemeetings.service.impl;

import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;

import agilemeetings.model.Group;
import agilemeetings.model.Permission;
import agilemeetings.model.PermissionGranted;
import agilemeetings.model.User;


public class AuthenticationUserDetails implements org.springframework.security.core.userdetails.UserDetails, agilemeetings.service.UserDetails
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
    private final String login;
    private final String passwordHash;
    private final boolean enabled;
    private Group group;
    private HashSet<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
    private static Logger log=LogManager.getLogger(AuthenticationUserDetails.class);

    public AuthenticationUserDetails(User user) 
    {
    	log.trace("Estoy en AuthenticationUserDetails (constructor)");
        this.login = user.getUsername();
        this.passwordHash = user.getPassword();
        this.enabled = (user.getEnabled()==1 ? true:false);
        // Convierto los permisos leidos de la base, para el grupo al que pertenece el usuario, en Authorities.
        Group g=user.getGroup();
        this.group=g;
        for(Permission p:g.getPermissions())
        {
        	PermissionGranted pg=new PermissionGranted(p.getAuthority());
        	log.trace("Obtuve el permiso "+pg.getAuthority());
        	this.grantedAuthorities.add(pg);
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() 
    {
    	log.trace("Estoy en AuthenticationUserDetails.getAuthorities");
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
    	log.trace("Estoy en AuthenticationUserDetails.getPassword");
        return passwordHash;
    }

    @Override
    public String getUsername() {
    	log.trace("Estoy en AuthenticationUserDetails.getUsername");
        return login;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountNonExpired() {
    	log.trace("Estoy en isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
    	log.trace("Estoy en isAccountNonLocked");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
    	log.trace("Estoy en isCredentialsNonExpired");
        return true;
    }

    public String getLogin() {
    	log.trace("Estoy en getLogin");
        return login;
    }

	public Long getId() {
		log.trace("Estoy en getId");
		return id;
	}

	@Override
	public Group getGroup() 
	{
		return this.group;
	}
}