package rw.app.urugendo.day.services.company.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.models.Driver.boardingDriver.dto.BoardingDriverDto;
import rw.app.urugendo.models.Driver.boardingDriver.dto.CreateBoardingDriverDto;
import rw.app.urugendo.services.Driver.boarding.impl.DriverServiceImpl;
import rw.app.urugendo.services.company.CompanyDriversServices;
import rw.app.urugendo.services.utils.CustomExceptionHandler;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyDriversServicesImpl implements CompanyDriversServices {

    private final DriverServiceImpl driverService;
    @Override
    public BoardingDriverDto registerDriver(CreateBoardingDriverDto createBoardingDriverDto) throws CustomExceptionHandler {
        return driverService.registerDriver(createBoardingDriverDto);
    }

    @Override
    public List<BoardingDriverDto> getDriversOnCompany(UUID companyId) {
        return driverService.getDriversOnCompany(companyId);
    }
}
