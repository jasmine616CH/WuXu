package config.convert;

import module.goods.dto.recordDTO;
import module.goods.dto.rfidDTO;
import module.goods.vo.rfidReturnDataVo;

import java.time.LocalDateTime;

public class recordVoToDto {

    public static recordDTO VTD(rfidReturnDataVo rfidReturnDataVo){

        recordDTO recordDTO = new recordDTO();
        recordDTO.setID(rfidReturnDataVo.getID());
        recordDTO.setName(rfidReturnDataVo.getName());
        recordDTO.setBorrowing(rfidReturnDataVo.getBorrowing());
        recordDTO.setTime(LocalDateTime.now());

        return recordDTO;

    }

}
