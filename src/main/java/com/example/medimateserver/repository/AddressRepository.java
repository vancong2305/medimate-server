package com.example.medimateserver.repository;

import com.example.medimateserver.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    List<Address> findByIdUser(Integer id);
}
