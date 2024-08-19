package rw.app.urugendo.day.services.company.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.models.Bus.boardingBus.dto.BusDto;
import rw.app.urugendo.models.Bus.boardingBus.dto.CreateBusDto;
import rw.app.urugendo.services.Bus.boarding.impl.BoardingBusServiceImpl;
import rw.app.urugendo.services.company.CompanyBusesServices;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyBusesServicesImpl implements CompanyBusesServices {

    private final BoardingBusServiceImpl busService;
    @Override
    public BusDto registerBus(CreateBusDto createBusDto) {
        return busService.registerBus(createBusDto);
    }

    @Override
    public List<BusDto> getAllBusesOnCompany(UUID companyId) {
        return busService.getBusesOnCompany(companyId);
    }
}
