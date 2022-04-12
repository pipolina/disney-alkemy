package com.alkemy.disney.repository;

import com.alkemy.disney.entity.GenderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenderRepository extends JpaRepository<GenderEntity,Long> {
}
