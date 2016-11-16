package main.service

import main.models.Role;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;


@Transactional
public interface RoleRepository extends CrudRepository<Role, Long> {


    //public User findByName(String name);

    /*@Query("SELECT new ")
    CurrencySummModel findCurrencySumm();*/

}
