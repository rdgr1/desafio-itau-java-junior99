package org.rod.itau.challenge.bank99.repo;

import org.rod.itau.challenge.bank99.model.TransacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TransacaoRepo extends JpaRepository<TransacaoModel, UUID> {
}
