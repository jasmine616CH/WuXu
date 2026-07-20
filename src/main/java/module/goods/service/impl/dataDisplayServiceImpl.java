package module.goods.service.impl;

import module.goods.dto.dataDisplayDTO;
import module.goods.mapper.dataDisplayMapper;
import module.goods.service.dataDisplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class dataDisplayServiceImpl implements dataDisplayService {

    @Autowired
    private dataDisplayMapper dataDisplayMapper;

    /**
     * 物品使用情况展示
     * @return 物品使用情况列表
     */
    @Override
    public List<dataDisplayDTO> dataDisplay() {

        List<dataDisplayDTO> dataDisplayDTOS = dataDisplayMapper.findAllItem();
        return dataDisplayDTOS.stream()
                .map(dataDisplayDTO -> new dataDisplayDTO(dataDisplayDTO.getUsageCount() , dataDisplayDTO.getName()))
                .collect(Collectors.toList());

    }
}
