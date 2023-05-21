package com.geekster.Blogging.Platform.API.Repository;

import com.geekster.Blogging.Platform.API.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepo extends CrudRepository<User,Long> {
    User findFirstByUserEmail(String userEmail);

    User findByUserId(Long myid);
}
