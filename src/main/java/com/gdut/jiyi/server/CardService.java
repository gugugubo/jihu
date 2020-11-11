package com.gdut.jiyi.server;

import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.vo.CardVo;

import java.util.List;

/**
 * @author 古春波
 * @description 卡片管理
 * @Date 2020/11/11 15:12
 * @version 1.0
 **/
public interface CardService {
    ResultVo<Card> addCard(CardVo card, String cookie);

    ResultVo<Integer> delCard(Integer cardId, String cookie);

    Integer delCardByBagId(Integer cardBagId);

    ResultVo<Card> upDateCard(CardVo card, Integer cardId, String cookie);

    List<Card> getByCardBagId(Integer cardBagId, Integer pageSize, Integer pageNum);
}
