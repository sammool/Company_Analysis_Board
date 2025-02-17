package sammool.board.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import sammool.board.domain.User;
import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(User user);
    User getById(@Param("id") Long id);
    Optional<User> getByEmail(@Param("email") String email);
}

