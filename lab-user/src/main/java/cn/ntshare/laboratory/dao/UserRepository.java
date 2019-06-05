package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByUsername(String username);

    User findOneByUsernameAndPassword(String username, String password);

    // todo Hibernate进行连表查询
    // 参考 https://blog.csdn.net/johnf_nash/article/details/80587204
}
