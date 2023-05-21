package com.geekster.Blogging.Platform.API.Repository;

import com.geekster.Blogging.Platform.API.model.AuthenticationToken;
import com.geekster.Blogging.Platform.API.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthenticationRepo extends CrudRepository<AuthenticationToken,Long> {

    AuthenticationToken findByUser(User user);

    AuthenticationToken findFirstByToken(String token);
}
