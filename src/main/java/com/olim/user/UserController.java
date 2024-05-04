package com.olim.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
//    Logger logger = new Logger(User.class);

    @PostMapping("/save")
    public String saveUser(@RequestBody User user){
        System.out.println(user);
        if (userService.saveUser(user)){
            return "User saved Successfully";
        }else{
            return "User not saved";
        }

    }
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<User> getUserId(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUser(id));
    }
    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserId(@PathVariable Long id){
        if(userService.deleteUser(id)){
            return ResponseEntity.ok("User Deleted Successfully");
        }
        return ResponseEntity.status(412).body("User id Does not exist");
    }

}
