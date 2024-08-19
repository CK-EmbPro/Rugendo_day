package rw.app.urugendo.day.services.company;

import rw.app.urugendo.models.Bus.boardingBus.dto.BusDto;
import rw.app.urugendo.models.Bus.boardingBus.dto.CreateBusDto;

import java.util.List;
import java.util.UUID;

public interface CompanyBusesServices {
    BusDto registerBus(CreateBusDto createBusDto);
    List<BusDto> getAllBusesOnCompany(UUID companyId);

}
