package cn.ntshare.laboratory.mapper.slave;

import cn.ntshare.laboratory.domain.SUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class SUserMapperTest {

    @Autowired
    private SUserMapper sUserMapper;

    @Test
    public void testSelectAll() {
        List<SUser> users = sUserMapper.selectAll();
        users.forEach(e -> System.out.println(e.getUsername()));
    }
}