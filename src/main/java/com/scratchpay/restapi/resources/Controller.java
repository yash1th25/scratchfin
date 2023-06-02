package com.scratchpay.restapi.resources;

import com.scratchpay.restapi.UserEntity.User;
import com.scratchpay.restapi.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class Controller {

    @Autowired
    private UserDao userdao;

    //@PostMapping("/users")
    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public int addUser(@RequestBody User user){
        return userdao.save(user);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> getUsersList(){

        return userdao.getUserList();
    }
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User getUserById(@PathVariable int id){

        return userdao.getUser(id);
    }

}
