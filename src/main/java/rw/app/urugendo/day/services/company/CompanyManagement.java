package rw.app.urugendo.day.services.company;

import rw.app.urugendo.models.Company.dto.CompanyDto;
import rw.app.urugendo.models.Company.dto.CreateCompanyDto;
import rw.app.urugendo.services.utils.CustomExceptionHandler;

import java.util.List;
import java.util.UUID;

public interface CompanyManagement {
    CompanyDto registerCompany(CreateCompanyDto createCompanyDto) throws CustomExceptionHandler;
    CompanyDto updateCompany(UUID companyId, CompanyDto companyDto);
    List<CompanyDto> getCompanies();
    boolean deleteCompany(UUID companyId);
    CompanyDto getCompany(UUID companyId);
}
