package com.sebit.clientsystem.repository;

import com.sebit.clientsystem.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client,Long> {
}
