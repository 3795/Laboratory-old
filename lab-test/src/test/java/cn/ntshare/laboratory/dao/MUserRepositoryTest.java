package cn.ntshare.laboratory.dao;

import cn.ntshare.laboratory.domain.master.MUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class MUserRepositoryTest {

    @Autowired
    private MUserRepository mUserRepository;

    @Test
    public void test() {
        MUser user = new MUser();
        user.setUsername("Tom");
        user = mUserRepository.save(user);
        System.out.println(user.getId());
    }

    @Test
    public void otherTest() {
        System.out.println("我是测试");
    }
}