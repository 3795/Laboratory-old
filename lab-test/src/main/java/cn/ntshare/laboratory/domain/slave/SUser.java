package cn.ntshare.laboratory.domain.slave;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "s_user")
@Getter
@Setter
@NoArgsConstructor
public class SUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
}
