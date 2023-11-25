package com.milan.doctorsNepal.repository;

import com.milan.doctorsNepal.entity.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IdentityRepository extends JpaRepository<Identity, Long> {

    List<Identity> findByNameContaining(String name);
    List<Identity> findBySpecialOnContaining(String specialOn);
    List<Identity> findByAddressContaining(String address);
    List<Identity> getIdentityByNameOrAddressContaining(String name, String address);

    Identity findByNameAndEmail(String name, String email);
}
