package cn.ntshare.laboratory.mapper.master;

import cn.ntshare.laboratory.domain.MUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MUserMapper {

    List<MUser> selectAll();
}
