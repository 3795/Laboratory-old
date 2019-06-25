package cn.ntshare.laboratory.mapper.slave;

import cn.ntshare.laboratory.domain.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SUserMapper {

    List<SUser> selectAll();
}
