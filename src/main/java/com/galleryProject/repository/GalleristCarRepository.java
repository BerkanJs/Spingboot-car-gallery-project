package com.galleryProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galleryProject.model.GalleristCar;

@Repository
public interface GalleristCarRepository extends JpaRepository<GalleristCar,Long> {

}
