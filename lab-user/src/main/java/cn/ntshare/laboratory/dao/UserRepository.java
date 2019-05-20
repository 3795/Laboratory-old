package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);
}
