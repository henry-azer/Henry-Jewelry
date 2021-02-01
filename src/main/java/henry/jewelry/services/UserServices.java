package henry.jewelry.services;

import henry.jewelry.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService {

    User getUserByUsername(String username);
    User getCurrentLoggedUser();
    boolean isAuthenticatedUser();
}
