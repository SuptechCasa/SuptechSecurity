package suptech.casa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import suptech.casa.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
public UserDetails findByUsername(String username);
}
