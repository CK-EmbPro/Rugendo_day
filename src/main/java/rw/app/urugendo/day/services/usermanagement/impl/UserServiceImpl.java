package rw.app.urugendo.day.services.usermanagement.impl;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rw.app.urugendo.day.Exceptions.BadRequestException;
import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.usermanagement.Enums.ERoles;
import rw.app.urugendo.day.models.usermanagement.User;
import rw.app.urugendo.day.models.usermanagement.dto.LogInDetailsDTO;
import rw.app.urugendo.day.models.usermanagement.dto.LoginDTO;
import rw.app.urugendo.day.models.usermanagement.dto.UserRegistrationDTO;
import rw.app.urugendo.day.repositories.usermanagement.IUserRepository;
import rw.app.urugendo.day.services.security.JwtAuthentication;
import rw.app.urugendo.day.services.usermanagement.IUserService;
import rw.app.urugendo.day.utils.BearerTokenWrapper;
import rw.app.urugendo.day.utils.CustomExceptionHandler;
import rw.app.urugendo.day.utils.EmailPasswordValidator;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final JwtAuthentication jwtAuthentication;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final BearerTokenWrapper bearerTokenWrapper;


    public UserServiceImpl(IUserRepository userRepository, JwtAuthentication jwtAuthentication, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, BearerTokenWrapper bearerTokenWrapper) {
        this.userRepository = userRepository;
        this.jwtAuthentication = jwtAuthentication;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.bearerTokenWrapper = bearerTokenWrapper;
    }

    @Override
    public LogInDetailsDTO registerUser(UserRegistrationDTO registrationDTO)  {
        LogInDetailsDTO loginDetails = null;
        try {
            if (EmailPasswordValidator.isValidGmail(registrationDTO.getEmail()) && EmailPasswordValidator.isValidPassword(registrationDTO.getPassword())) {
                User user = new User(registrationDTO.getPhoneNumber(), registrationDTO.getFirstName(), registrationDTO.getLastName(), passwordEncoder.encode(registrationDTO.getPassword()), registrationDTO.getNationalId(), registrationDTO.getEmail(), ERoles.PARENT, registrationDTO.getGender());
                try {
                    user = userRepository.save(user);
                } catch (DuplicateKeyException duplicateKeyException) {
                    throw new DuplicateKeyException("User already exists");
                }

                Map<String, Object> claims = new HashMap<>();
                claims.put("role", user.getRoles());
                String token = jwtAuthentication.generateToken(claims, user);
                loginDetails = new LogInDetailsDTO(token, ERoles.PARENT, user);
            } else {
                if (!EmailPasswordValidator.isValidPassword(registrationDTO.getPassword())) {
                    throw new BadRequestException("Password is not valid");
                } else if (!EmailPasswordValidator.isValidGmail(registrationDTO.getEmail())) {
                    throw new BadRequestException("Email is not valid");

                }

            }

        }catch (BadRequestException e){
            log.error(e.getMessage());
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }

        return loginDetails;

    }

    @Override
    public LogInDetailsDTO login(LoginDTO loginDTO) {
        LogInDetailsDTO loginDetails = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
            User user = userRepository.findByEmail(loginDTO.getEmail());
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRoles());
            String token = jwtAuthentication.generateToken(claims, user);
            if (user.getRoles().equals(ERoles.PARENT)) {
                loginDetails=  new LogInDetailsDTO(token, ERoles.PARENT, user);

            }

        }catch (Exception e){
            log.error("Something bad happened: {}",e.getMessage());
        }

        return loginDetails;


    }

    @Override
    public LogInDetailsDTO getCurrentUser() {
        LogInDetailsDTO loginDetails = null;
        try {
            String token = bearerTokenWrapper.getToken();
            String email = jwtAuthentication.getUniqueIdentifier(token);
            User user = userRepository.findByEmail(email);
            if (user.getRoles().equals(ERoles.PARENT)) {
                loginDetails= new LogInDetailsDTO(token, ERoles.PARENT, user);
            }
        }catch (Exception e){
            log.error("Something bad happened: {}", e.getMessage());
        }
        return loginDetails;


    }
}
//
//    @Override
//    public User registerCompany(String companyPhono, String companyName, String companyEmail, String password) throws CustomExceptionHandler {
//        if (EmailPasswordValidator.isValidGmail(companyEmail)&& EmailPasswordValidator.isValidPassword(password)){
//            String[] parts = companyName.trim().split(" ", 2);
//            String companyFirstName = parts[0];
//            String compLastName = parts.length > 1 ? parts[1] : "";
//
//            User newUser = new User(companyPhono, companyFirstName, compLastName, companyEmail, password);
//
//            return userRepository.save(newUser);
//        }else{
//            if(!EmailPasswordValidator.isValidGmail(companyEmail)){
//                throw new CustomExceptionHandler("Email is not valid");
//            }
//            if(!EmailPasswordValidator.isValidPassword(password)){
//                throw new CustomExceptionHandler("Password is not valid");
//            }
//        }
//
//        return null;
//
//    }






