package module.goods.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import module.goods.dto.informationQueryDTO;
import module.goods.entity.goods;
import module.goods.mapper.goodsMapper;
import module.goods.service.informationQueryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 物品情况查询业务实现类
 */
@Service
public class informationQueryServiceImpl extends  ServiceImpl<goodsMapper, goods> implements informationQueryService {

    /**
     * 根据条件查询物品使用情况
     * @param informationQueryDTO 查询条件
     * @return 物品实体类列表
     */
    @Override
    public List<goods> informationQuery(informationQueryDTO informationQueryDTO) {

        //1.构造条件构造器
        LambdaQueryWrapper<goods> wrapper = new LambdaQueryWrapper<>();

        //2.动态拼接条件
        wrapper.like(StringUtils.hasText(informationQueryDTO.getName()) , goods::getName , informationQueryDTO.getName())
                .eq(informationQueryDTO.getID() != null , goods::getID , informationQueryDTO.getID())
                .eq(informationQueryDTO.getProperties() != null , goods::getProperties , informationQueryDTO.getProperties())
                .eq(informationQueryDTO.getState() != null , goods::getState , informationQueryDTO.getState());

        //3.执行查询
        if (informationQueryDTO.getPageNum() != null && informationQueryDTO.getPageSize() !=null){
            Page<goods> page = new Page<>(informationQueryDTO.getPageNum() , informationQueryDTO.getPageSize());
            return page(page , wrapper).getRecords();
        }

        return list(wrapper);
    }
}
