package com.geekster.Blogging.Platform.API.Repository;

import com.geekster.Blogging.Platform.API.model.BlogComment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface ICommentRepo extends CrudRepository<BlogComment,Long> {
}
