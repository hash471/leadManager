package com.sriharsha.lead.manager;

import com.sriharsha.lead.daos.LeadRepository;
import com.sriharsha.lead.daos.UserRepository;
import com.sriharsha.lead.model.JsonLeadData;
import com.sriharsha.lead.model.Lead;
import com.sriharsha.lead.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefaultUserManager implements UserManager{

    private final UserRepository userRepository;
    private final LeadRepository leadRepository;

    public DefaultUserManager(
            UserRepository userRepository,
            LeadRepository leadRepository
    ) {
        this.userRepository = userRepository;
        this.leadRepository = leadRepository;
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByHook(String hook) {
        return userRepository.findByWebhook(hook);
    }

    @Override
    public User addUser(long id) {
        User user = new User();
        try {
            user.setId(id);
            user.setLeads(Collections.emptySet());
            user.setWebhook(UUID.randomUUID().toString());
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println("Failed to save User because of exception:" + e);
        }
        return user;
    }

    @Override
    public void addLead(String hook, JsonLeadData jsonLead) throws Exception {
        User user = this.userRepository.findByWebhook(hook).orElse(null);

        // Validation is needed for name in terms of the length having min 3 characters
        // Phone number if internation code is provided then should start with + and length more than 10 characters
        // Check to see if the email is a valid Email format.
        // Other Details , I would prefer to store by using changing using JSON Object from gson library and then to string in database rather than HashMap

        if(Objects.nonNull(user)) {
            Set<Lead> leads = user.getLeads();
            Lead lead = new Lead();
            lead.setEmail(jsonLead.getEmail());
            lead.setName(jsonLead.getName());
            lead.setOtherDetails(jsonLead.getOtherFields().toString());
            lead.setPhoneNumber(jsonLead.getPhoneNumber());
            leads.add(leadRepository.save(lead));
            user.setLeads(leads);
            userRepository.save(user);
        } else {
            throw new Exception("User not found for the webhook");
        }

    }

}
