package com.example.demo.Repository;

import com.example.demo.Model.SpotAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotAddressRepository extends JpaRepository<SpotAddress,Integer> {
}
