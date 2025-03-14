package com.example.companyms.company.service;

import com.example.companyms.company.entity.Company;
import com.example.companyms.company.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService{
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company findCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public boolean updateCompanyById(Long id, Company company) {
        Optional<Company> companyById = companyRepository.findById(id);
        if(companyById.isPresent()){
            Company currentCompany = companyById.get();
            currentCompany.setName(company.getName());
            currentCompany.setDescription(company.getDescription());
            companyRepository.save(currentCompany);
            return true;
        }
        return false;
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompany(Long id) {
        Optional<Company> companyToDelete = companyRepository.findById(id);
        if(companyToDelete.isPresent()){
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
