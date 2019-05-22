package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.domain.slave.SUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SUserRepository extends JpaRepository<SUser, Integer> {
}
