package config.convert;

import module.goods.dto.rfidDTO;
import module.goods.vo.rfidReturnDataVo;

import java.time.LocalDateTime;

public class rfidVoToDto {

    public static rfidDTO VTD(rfidReturnDataVo rfidReturnDataVo){

        rfidDTO rfidDTO = new rfidDTO();
        rfidDTO.setID(rfidReturnDataVo.getID());
        rfidDTO.setName(rfidReturnDataVo.getName());
        rfidDTO.setState(rfidReturnDataVo.getState());
        rfidDTO.setBorrowing(rfidReturnDataVo.getBorrowing());
        rfidDTO.setTime(LocalDateTime.now());

        return rfidDTO;

    }

}
