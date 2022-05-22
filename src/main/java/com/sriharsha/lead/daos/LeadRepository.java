package com.sriharsha.lead.daos;

import com.sriharsha.lead.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadRepository extends JpaRepository<Lead, Integer> {
}