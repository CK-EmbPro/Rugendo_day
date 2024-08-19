package rw.app.urugendo.day.services.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rw.app.urugendo.models.usermanagement.User;
import rw.app.urugendo.repositories.usermanagement.IUserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findFirstByEmail(username);
        if (user.isPresent()){
            return new org.springframework.security.core.userdetails.User(user.get().getEmail(),user.get().getPassword(), List.of(new SimpleGrantedAuthority(user.get().getRoles().name())));
        }
        throw new UsernameNotFoundException("User not found") ;
    }
}
