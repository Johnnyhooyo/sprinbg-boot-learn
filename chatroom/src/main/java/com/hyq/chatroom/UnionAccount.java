package com.hyq.chatroom;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author：huyuanqiang
 * @time: 2020-08-07 20:27
 * @description:
 **/
public class UnionAccount {
    private Integer id;
    private Long no;
    private String name;
    private Date createTime;

    private BigDecimal liveAmount;
    private Integer monthTailLiverNum;
    private Integer newLiverNum;
    private Integer validLiverNum;
    private Integer newValidLiverNum;
    private Integer lastNewValidLiverNum;

    private BigDecimal liverAbleValue;
    private BigDecimal lastLiverAbleValue;

    private String type;

    private String level;
    private BigDecimal fixPercent;
    private BigDecimal taskPercent;


}
