package com.sriharsha.lead.manager;

import com.sriharsha.lead.daos.LeadRepository;
import com.sriharsha.lead.daos.UserRepository;
import com.sriharsha.lead.model.JsonLeadData;
import com.sriharsha.lead.model.User;

import java.util.Optional;

public interface UserManager {
    Optional<User> findUserById(long Id);

    Optional<User> findUserByHook(String hook);

    User addUser(long id);

    void addLead(String hook, JsonLeadData lead) throws Exception;
}
