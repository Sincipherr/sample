package com.geekster.Blogging.Platform.API.Repository;

import com.geekster.Blogging.Platform.API.model.Following;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowingRepo extends CrudRepository<Following,Long> {
}
