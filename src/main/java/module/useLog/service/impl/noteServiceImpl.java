package module.useLog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import module.useLog.dto.noteDTO;
import module.useLog.mapper.noteMapper;
import module.useLog.service.noteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 使用记录查询业务接口实现类
 */
@Service
public class noteServiceImpl implements noteService {

    @Autowired
    private noteMapper noteMapper;

    /**
     * 查询物品使用情况
     */
    @Override
    public IPage<noteDTO> useFind(noteDTO noteDTO) {

        //1.自动分页
        Page<noteDTO> page = new Page<>(noteDTO.getPageNum() , noteDTO.getPageSize());

        //2.条件查询
        QueryWrapper<noteDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("ID" , noteDTO.getID())
                .like("goodsName" , noteDTO.getName())
                .like("userID" , noteDTO.getUserID())
                .like("borrowingTime" , noteDTO.getBorrowingTime())
                .like("backTime" , noteDTO.getBackTime());

        //3.执行分页条件查询
        return noteMapper.selectPage(page , queryWrapper);

    }
}
