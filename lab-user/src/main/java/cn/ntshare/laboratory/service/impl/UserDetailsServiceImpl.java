package cn.ntshare.laboratory.service.impl;

import cn.ntshare.laboratory.dao.UserRepository;
import cn.ntshare.laboratory.domain.User;
import com.sun.jmx.snmp.internal.SnmpSecurityModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2019/6/5
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found with username" + username);
        }
//        return ;
        return null;
    }
}
