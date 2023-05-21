package com.geekster.Blogging.Platform.API.Repository;

import com.geekster.Blogging.Platform.API.model.Follower;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFollowerRepo extends CrudRepository<Follower,Long> {
}
