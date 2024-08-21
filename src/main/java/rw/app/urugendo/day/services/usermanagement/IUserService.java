package rw.app.urugendo.day.services.usermanagement;

import rw.app.urugendo.day.Exceptions.ResourceNotFoundException;
import rw.app.urugendo.day.models.usermanagement.User;
import rw.app.urugendo.day.models.usermanagement.dto.LogInDetailsDTO;
import rw.app.urugendo.day.models.usermanagement.dto.LoginDTO;
import rw.app.urugendo.day.models.usermanagement.dto.UserRegistrationDTO;
import rw.app.urugendo.day.utils.CustomExceptionHandler;

public interface IUserService {
    LogInDetailsDTO registerUser(UserRegistrationDTO registrationDTO) throws CustomExceptionHandler;
    LogInDetailsDTO login(LoginDTO loginDTO) throws ResourceNotFoundException;
    LogInDetailsDTO getCurrentUser() throws ResourceNotFoundException;
    User registerCompany(String companyPhono, String compnanyName, String email, String password) throws CustomExceptionHandler;
}
