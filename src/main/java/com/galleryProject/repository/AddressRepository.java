package com.galleryProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galleryProject.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long > {

}
