package com.kushan.priceenginecloud.service.impl;

import com.kushan.priceenginecloud.entity.Parameter;
import com.kushan.priceenginecloud.repository.ParameterRepository;
import com.kushan.priceenginecloud.service.ParameterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ParameterServiceImpl implements ParameterService {

    private final ParameterRepository parameterRepository;

    @Override
    public Optional<Parameter> getParameter() {
        return parameterRepository.findFirstBy();
    }

}
