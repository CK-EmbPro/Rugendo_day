package rw.app.urugendo.day.services.company;

import rw.app.urugendo.models.Company.CompanyRoutes;
import rw.app.urugendo.models.Company.dto.CompanyRoutesDto;
import rw.app.urugendo.models.Company.dto.CreateCompanyRoutesToSaveDto;

import java.util.List;
import java.util.UUID;

public interface CompanyRoutesService {

    List<CompanyRoutesDto> registerRoute(List<CreateCompanyRoutesToSaveDto> routesToSaveDto);
    void deleteAllRoute(List<CompanyRoutes> routesToBeDeleted);
    List<CompanyRoutes> getRoutesByCompanyId(UUID companyId);

}
