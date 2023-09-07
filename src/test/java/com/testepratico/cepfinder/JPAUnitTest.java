package com.testepratico.cepfinder;

import com.testepratico.cepfinder.models.CustomerAddressListModel;
import com.testepratico.cepfinder.repositories.CustomerAddressListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class JPAUnitTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CustomerAddressListRepository addressRepository;

    @Test
    void shouldnt_find_if_repository_is_empty() {
        Iterable tutorials = addressRepository.findAll();

        assertThat(tutorials).isEmpty();
    }

    @Test
    void should_record_an_address() {
        CustomerAddressListModel address = new CustomerAddressListModel();
        address.setStreet("Rua Paraíso");
        address.setState("SP");
        address.setEmail("1@2.com");
        CustomerAddressListModel sAddress = addressRepository.save(address);

        assertThat(sAddress).hasFieldOrPropertyWithValue("street", "Rua Paraíso");
        assertThat(sAddress).hasFieldOrPropertyWithValue("email", "1@2.com");
    }

    @Test
    void should_find_address_by_email() {
        CustomerAddressListModel address1 = new CustomerAddressListModel();
        address1.setStreet("Rua Paraíso");
        address1.setState("SP");
        address1.setEmail("1@2.com");
        entityManager.persist(address1);

        CustomerAddressListModel address2 = new CustomerAddressListModel();
        address2.setStreet("Rua Hellraiser");
        address2.setState("PB");
        address2.setEmail("1@3.com");
        entityManager.persist(address2);

        List<CustomerAddressListModel> addrFound = addressRepository.findByEmail(address2.getEmail());
        assertThat(addrFound.get(0).getEmail()).isEqualTo("1@3.com");
    }
}