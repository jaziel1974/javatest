package com.testepratico.cepfinder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.testepratico.cepfinder.models.CustomerAddressListModel;

import java.util.List;
import java.util.UUID;

public interface CustomerAddressListRepository extends JpaRepository<CustomerAddressListModel, UUID> {
    List<CustomerAddressListModel> findByEmail(String email);
}