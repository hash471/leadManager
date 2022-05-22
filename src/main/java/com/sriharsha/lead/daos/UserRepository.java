package com.sriharsha.lead.daos;

import com.sriharsha.lead.model.Lead;
import com.sriharsha.lead.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findById(long id);

    Optional<User> findByWebhook(String hook);
}
