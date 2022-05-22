package com.sriharsha.lead.controller.api.v1;

import com.sriharsha.lead.manager.UserManager;
import com.sriharsha.lead.model.JsonLeadData;
import com.sriharsha.lead.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin("*")
public class UserController {

    private final UserManager userManager;

    public UserController(
            UserManager userManager
    ) {
        this.userManager = userManager;
    }

    @GetMapping({ "/leads/{userId}" })
    public ResponseEntity<User> getUser(@PathVariable("userId") Long userId) {
        User user = this.userManager.findUserById(userId).orElse(null);
        if(Objects.isNull(user)) {
            user = this.userManager.addUser(userId);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping({ "/webhook/{userHook}/add/lead"})
    public ResponseEntity<String> addLead(@PathVariable("userHook") String hook, @RequestBody JsonLeadData leadData) throws Exception {
        try {
            this.userManager.addLead(hook,leadData);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.toString(),HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<String>("success",HttpStatus.OK);
    }
}
