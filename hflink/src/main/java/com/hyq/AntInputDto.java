package com.hyq;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author：huyuanqiang
 * @time: 2020-08-20 14:07
 * @description:
 **/
@Data
public class AntInputDto implements Serializable {
    private String bizCode;
    private String inputType;
    private String uniqueId;
    private BigDecimal amount;
    private String matchKey;
    private Date tradeTime;

    private Long processTime;

    /**
     * 金额，频率，等
     */
    private String monitorType;
    private String monitorValueRight;
    private String monitorValueLeft;

    public static void main(String[] args) {
        AntInputDto antInputDto = new AntInputDto();
        antInputDto.setAmount(new BigDecimal(100));
        antInputDto.setBizCode("order");
        antInputDto.setInputType("biz");
        antInputDto.setMatchKey("m01");
        antInputDto.setUniqueId("sdijfisuf");

        System.out.println(JSON.toJSONString(antInputDto));
    }
}
