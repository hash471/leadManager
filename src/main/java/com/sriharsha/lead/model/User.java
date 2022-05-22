package com.sriharsha.lead.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "leads")
    private Set<Lead> leads;

    @Column(name="webhook")
    private String webhook;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWebhook() {
        return webhook;
    }

    public void setWebhook(String webhook) {
        this.webhook = webhook;
    }

    public Set<Lead> getLeads() {
        return leads;
    }

    public void setLeads(Set<Lead> leads) {
        this.leads = leads;
    }
}
