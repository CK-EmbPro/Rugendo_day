package rw.app.urugendo.day.services.company.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rw.app.urugendo.models.Company.Company;
import rw.app.urugendo.models.Company.dto.CompanyDto;
import rw.app.urugendo.models.Company.dto.CreateCompanyDto;
import rw.app.urugendo.models.Company.utils.CompanyMapper;
import rw.app.urugendo.repositories.company.CompanyRepository;
import rw.app.urugendo.services.company.CompanyManagement;
import rw.app.urugendo.services.usermanagement.IUserService;
import rw.app.urugendo.services.utils.CustomExceptionHandler;
import rw.app.urugendo.services.utils.EmailPasswordValidator;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyManagementImpl implements CompanyManagement {

//    private final CompanyRoutesServiceImpl routesService;
    private final CompanyRepository companyRepository;
    private final IUserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public CompanyDto registerCompany(CreateCompanyDto createCompanyDto) throws CustomExceptionHandler {
        Company company = CompanyMapper.CreatecompDtoToCompany(createCompanyDto, passwordEncoder);
        Company savedCompany=null;
        if(EmailPasswordValidator.isValidGmail(createCompanyDto.getCompanyEmail()) && EmailPasswordValidator.isValidPassword(createCompanyDto.getPassword())){
            savedCompany = companyRepository.save(company);

        }else {
            if(!EmailPasswordValidator.isValidGmail(createCompanyDto.getCompanyEmail())){
                throw new CustomExceptionHandler("Email is not valid");
            }
            if(!EmailPasswordValidator.isValidPassword(createCompanyDto.getPassword())){
                throw new CustomExceptionHandler("Password is not valid");
            }
        }
        userService.registerCompany(company.getCompanyPhono(), company.getCompanyName(),company.getCompanyEmail(), company.getPassword());
//        List<CreateCompanyRoutesToSaveDto> routesToSave = CompanyRoutesMapper.createRoutesDtoListToRoutesToSaveDtoList(createCompanyDto.getCompanyRoutesDto(), savedCompany.getCompanyId());
//        List<CompanyRoutesDto> savedRoutes = routesService.registerRoute(routesToSave);
//        List<CompanyRoutes> routesToBeSaved = CompanyRoutesMapper.dtoToSaveListToRoutesList(routesToSave);
        return CompanyMapper.companyToCompanyDto(savedCompany);

    }

    @Override
    public CompanyDto updateCompany(UUID companyId, CompanyDto companyDto) {
        Company company = companyRepository.findCompanyByCompanyId(companyId).orElseThrow(()-> new EntityNotFoundException("Company not found with id "+companyId));
        company.setCompanyEmail(companyDto.getCompanyEmail());
        company.setCompanyName(companyDto.getCompanyName());
        company.setCompanyPhono(companyDto.getCompanyPhono());

        Company updatedCompany = companyRepository.save(company);
        return CompanyMapper.companyToCompanyDto(updatedCompany);
    }

    @Override
    public List<CompanyDto> getCompanies() {
        List<Company> companies = companyRepository.findAll();

        return companies
                .stream()
                .map(
                        CompanyMapper::companyToCompanyDto
                )
                .toList();
    }

    @Override
    public boolean deleteCompany(UUID companyId) {
        Company company = companyRepository.findCompanyByCompanyId(companyId).orElseThrow(()-> new EntityNotFoundException("Company not found with id "+companyId));
        companyRepository.delete(company);
//        List<CompanyRoutes> routesToBeDeleted = routesService.getRoutesByCompanyId(companyId);
//        routesService.deleteAllRoute(routesToBeDeleted);
        return !companyRepository.existsById(companyId);
    }

    @Override
    public CompanyDto getCompany(UUID companyId) {
        Company company = companyRepository.findCompanyByCompanyId(companyId)
                .orElseThrow(()->new EntityNotFoundException("Company not found with id "+companyId));
        return CompanyMapper.companyToCompanyDto(company);
    }
}
