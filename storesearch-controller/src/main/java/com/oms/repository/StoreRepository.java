package com.oms.repository;

import com.oms.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {
    
    List<Store> findByZipCode(String zipCode);
}
