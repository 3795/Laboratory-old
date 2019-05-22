package cn.ntshare.laboratory.mapper.slave;

import cn.ntshare.laboratory.domain.SUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SUserMapper {

    List<SUser> selectAll();
}
