package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.domain.master.MUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MUserRepository extends JpaRepository<MUser, Integer> {
}
