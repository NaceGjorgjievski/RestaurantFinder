package mk.ukim.finki.backend.service.imp;

import mk.ukim.finki.backend.model.Exceptions.EmailAlreadyExistsException;
import mk.ukim.finki.backend.model.Exceptions.InvalidArgumentsException;
import mk.ukim.finki.backend.model.Exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.backend.model.User;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String email, String password) {
        if(!email.contains("@")){
            throw new InvalidArgumentsException("email must contain '@'");
        }
        User user = userRepository.findByEmailAndPassword(email, password);
        if(user == null){
            throw new InvalidUserCredentialsException();
        }
        return user;
    }

    @Override
    public User register(String username, String email, String password) {
        if(!email.contains("@")){
            throw new InvalidArgumentsException("email must contain '@'");
        }
        if(userRepository.findByEmail(email) != null){
            throw new EmailAlreadyExistsException(email);
        }
        User user = new User(username, email, password);
        return userRepository.save(user);
    }
}
