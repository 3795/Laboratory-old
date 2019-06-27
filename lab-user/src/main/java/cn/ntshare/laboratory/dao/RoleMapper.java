package cn.ntshare.laboratory.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMapper {

    List<String> findRoleByUserId(@Param("userId") Integer userId);
}
