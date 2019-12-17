package com.cognitive.zadatak.repositories;

import com.cognitive.zadatak.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
