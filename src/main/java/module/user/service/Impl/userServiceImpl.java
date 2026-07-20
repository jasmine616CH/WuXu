package module.user.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import common.exception.BusinessException;
import common.result.ResultCode;
import lombok.RequiredArgsConstructor;
import module.user.entity.User;
import module.user.entity.eduStudent;
import module.user.entity.eduTeacher;
import module.user.mapper.eduStudentMapper;
import module.user.mapper.eduTeacherMapper;
import module.user.mapper.userMapper;
import module.user.service.userService;
import module.user.vo.userProfileVo;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * 系统用户服务实现类
 */
@Service
@RequiredArgsConstructor
public class userServiceImpl extends ServiceImpl<userMapper , User> implements userService {

    private final userMapper userMapper;
    private final eduStudentMapper eduStudentMapper;
    private final eduTeacherMapper eduTeacherMapper;


    /**
     * 根据用户ID判断用户是否存在
     */
    @Override
    public boolean existsByID(String ID) {
        return getById(ID) == null;
    }

    /**
     * 获取用户展示信息
     */
    @Override
    public userProfileVo getUserProfile(String ID) {

        //1.查询用户基本信息
        User user = userMapper.selectById(ID);
        if (Objects.isNull(user)){
            throw new BusinessException(ResultCode.USER_NOT_FOUND);
        }
        Integer userType = user.getPermission();

        //2.封装基础Vo
        userProfileVo userProfileVo = new userProfileVo();
        BeanUtil.copyProperties(user , userProfileVo);

        //3.根据用户类型详细查询
        return switch (userType){

            case 1 ->getStudentDetail(user , userProfileVo);
            case 0 ->getTeacherDetail(user , userProfileVo);
            default -> userProfileVo;

        };

    }

    /**
     * 封装学生详细信息
     */
    private userProfileVo getStudentDetail(User user , userProfileVo userProfileVo){

        eduStudent student = eduStudentMapper.selectOne(Wrappers
                .lambdaQuery(eduStudent.class)
                .eq(eduStudent::getID , user.getID()));
        if (Objects.nonNull(student)){
            BeanUtil.copyProperties(student , userProfileVo , "ID");
        }

        return userProfileVo;
    }

    /**
     * 封装教师详细信息
     */
    private userProfileVo getTeacherDetail(User user , userProfileVo userProfileVo){

       eduTeacher teacher = eduTeacherMapper.selectOne(Wrappers
               .lambdaQuery(eduTeacher.class)
               .eq(eduTeacher::getID , user.getID()));
       if (Objects.nonNull(teacher)){
           BeanUtil.copyProperties(teacher , userProfileVo , "ID");
       }

        return userProfileVo;
    }

}
