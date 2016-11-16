package main.controllers

import main.models.User
import main.service.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
public class UserController {

  @RequestMapping("/get-users")
  @ResponseBody
  public List<User> getUsers() {
    List<User> list = (List<User>) userRepository.findAll();
    def list2 = list.
    return list;
  }

  @RequestMapping("/create")
  @ResponseBody
  public String create(String name, String pass) {
    User user = null;
    try {
      user = new User(name, pass);
      userRepository.save(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created! (id = " + user.getId() + ")";
  }
  

  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userRepository.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  

  @RequestMapping("/get-by-name")
  @ResponseBody
  public String getByName(String name) {
    String userId;
    try {
      User user = userRepository.findByName(name);
      userId = String.valueOf(user.getId());
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "The user id is: " + userId;
  }
  

  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String name, String pass) {
    try {
      User user = userRepository.findOne(id);
      user.setName(name);
      user.setPass(pass);
      userRepository.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }


  @Autowired
  private UserRepository userRepository;
  
} // class UserController
