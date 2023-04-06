package com.inditex.microservice.repository;

import com.inditex.microservice.model.PriceZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceZoneRepository extends JpaRepository<PriceZone, Integer>, JpaSpecificationExecutor<PriceZone> {
}
