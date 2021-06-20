package com.kushan.priceenginecloud.repository;

import com.kushan.priceenginecloud.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParameterRepository extends JpaRepository<Parameter, Long> {

    Optional<Parameter> findFirstBy();

}
