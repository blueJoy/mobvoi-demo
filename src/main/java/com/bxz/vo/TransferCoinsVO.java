package com.bxz.vo;

import lombok.Data;

/**
 * @author baixiangzhu
 * @date 2018/5/22
 **/
@Data
public class TransferCoinsVO extends CoinsVO {
    private static final long serialVersionUID = 6077414144576731758L;

    private Long fromUserId;

    private Long toUserId;
}
