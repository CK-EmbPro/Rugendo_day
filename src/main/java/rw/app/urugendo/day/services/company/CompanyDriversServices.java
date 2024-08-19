package rw.app.urugendo.day.services.company;

import rw.app.urugendo.models.Driver.boardingDriver.dto.BoardingDriverDto;
import rw.app.urugendo.models.Driver.boardingDriver.dto.CreateBoardingDriverDto;
import rw.app.urugendo.services.utils.CustomExceptionHandler;

import java.util.List;
import java.util.UUID;

public interface CompanyDriversServices {
    BoardingDriverDto registerDriver(CreateBoardingDriverDto createBoardingDriverDto) throws CustomExceptionHandler;
    List<BoardingDriverDto> getDriversOnCompany(UUID companyId);
}
