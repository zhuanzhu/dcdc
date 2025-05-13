package com.egeo.components.product.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.egeo.components.user.client.CompanyClient;
import com.egeo.components.user.dto.CompanyDTO;

/**
 * Created by 0.0 on 2018/9/10.
 */
@Component
public class CompanyFacade {
    @Autowired
    private CompanyClient companyReadService;

    public CompanyDTO findCompanyById(Long companyId) {
        return companyReadService.findCompanyById(companyId);
    }

    public List<CompanyDTO> findCompayByName(String trim) {
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setCompanyName(trim);
        return companyReadService.findCompanyAll(companyDTO);
    }
}
