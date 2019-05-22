package cn.ntshare.laboratory.mapper.master;

import cn.ntshare.laboratory.domain.MUser;
import cn.ntshare.laboratory.domain.SUser;
import cn.ntshare.laboratory.mapper.slave.SUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MUserMapperTest {

    @Autowired
    private MUserMapper mUserMapper;

    @Autowired
    private SUserMapper sUserMapper;

    @Test
    public void test() {
        List<SUser> users = sUserMapper.selectAll();
        System.out.println(users.size());
//        System.out.println("我是测试");
    }
}