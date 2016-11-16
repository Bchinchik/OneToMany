package main.service

import main.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


@Transactional
public interface UserRepository extends JpaRepository<User, Long> {


  public User findByName(String name);

} // class UserRepository
