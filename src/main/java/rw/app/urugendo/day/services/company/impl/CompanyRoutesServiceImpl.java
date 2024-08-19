package rw.app.urugendo.day.services.company.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rw.app.urugendo.models.Company.CompanyRoutes;
import rw.app.urugendo.models.Company.dto.CompanyRoutesDto;
import rw.app.urugendo.models.Company.dto.CreateCompanyRoutesToSaveDto;
import rw.app.urugendo.models.Company.utils.CompanyRoutesMapper;
import rw.app.urugendo.repositories.company.CompanyRoutesRepository;
import rw.app.urugendo.services.company.CompanyRoutesService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyRoutesServiceImpl implements CompanyRoutesService {
    private final CompanyRoutesRepository routesRepository;

    @Override
    public List<CompanyRoutesDto> registerRoute(List<CreateCompanyRoutesToSaveDto> routesToSaveDto) {
        List<CompanyRoutes> savedRoutes=routesRepository.saveAll(CompanyRoutesMapper.dtoToSaveListToRoutesList(routesToSaveDto));

        return savedRoutes
                .stream()
                .map(CompanyRoutesMapper::companyRoutesToDto)
                .toList();
    }

    @Override
    public void deleteAllRoute(List<CompanyRoutes> routesToBeDeleted) {
       routesRepository.deleteAll(routesToBeDeleted);

    }

    @Override
    public List<CompanyRoutes> getRoutesByCompanyId(UUID companyId) {
        return routesRepository.findCompanyRoutesByCompanyId(companyId);
    }
}
