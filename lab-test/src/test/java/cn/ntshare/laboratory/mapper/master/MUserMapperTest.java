package cn.ntshare.laboratory.mapper.master;

import cn.ntshare.laboratory.domain.MUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MUserMapperTest {

    @Autowired
    private MUserMapper mUserMapper;

    @Test
    public void testSelectAll() {
        List<MUser> users = mUserMapper.selectAll();
        users.forEach(e -> System.out.println(e.getUsername()));
    }
}