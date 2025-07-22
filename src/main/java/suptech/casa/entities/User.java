package suptech.casa.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor  @AllArgsConstructor @Data
public class User implements UserDetails{

	@Id
	Long id;
	String username;
	String password;
	String roles;//ROLE_ADMIN;ROLE_USER;...
	boolean active;
	
	private List<String> getRoles() {
		if (this.roles==null) return List.of();
		return Arrays.asList(this.roles.split(";"));
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
		this.getRoles().forEach(role->{
			authorities.add(new SimpleGrantedAuthority(role));
		});
		return authorities;
	}
	
}
