package module.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import module.user.dto.userRegisterDTO;
import module.user.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface userMapper extends BaseMapper<User> {

    /**
     * 注册用户
     * @param request 学生教职工注册参数
     */
    @Insert("insert into wuxu.user(ID, username, name, password_hash, permission , phone, `email`) " +
            "VALUES (#{ID} , #{username} , #{name} , #{password_hash} , #{permission} , #{phone} , #{email})")
    void add(userRegisterDTO request);
}
