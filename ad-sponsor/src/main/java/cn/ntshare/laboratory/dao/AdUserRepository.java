package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.entity.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdUserRepository extends JpaRepository<AdUser, Long> {

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    AdUser findByUsername(String username);
}
