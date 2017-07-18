package agilemeetings.service;

import java.io.Serializable;
import java.util.*;
import org.springframework.security.core.GrantedAuthority;

import agilemeetings.model.Group;
import agilemeetings.model.Persona;

public interface UserDetails extends Serializable 
{
    Collection<GrantedAuthority> getAuthorities();
    String getPassword();
    String getUsername();
    Group getGroup();
    Persona getPersona();
    boolean isAccountNonExpired();
    boolean isAccountNonLocked();
    boolean isCredentialsNonExpired();
    boolean isEnabled();
}