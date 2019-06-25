package cn.ntshare.laboratory.mapper.master;

import cn.ntshare.laboratory.domain.MUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface MUserMapper {

    List<MUser> selectAll();
}
