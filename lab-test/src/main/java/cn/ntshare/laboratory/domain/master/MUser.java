package cn.ntshare.laboratory.domain.master;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "m_user")
@Getter
@Setter
@NoArgsConstructor
public class MUser {

    @Id
    @GeneratedValue
    private Integer id;

    private String username;
}
