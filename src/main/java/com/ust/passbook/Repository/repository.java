package com.ust.passbook.Repository;

import com.ust.passbook.model.expensive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface repository extends JpaRepository<expensive, UUID> {
}
