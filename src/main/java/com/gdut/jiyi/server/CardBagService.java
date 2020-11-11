package com.gdut.jiyi.server;

import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.CardBag;
import com.gdut.jiyi.vo.CardBagVo;

import java.util.List;

/**
 * @author 古春波
 * @description 卡包管理
 * @Date 2020/11/11 15:12
 * @version 1.0
 **/
public interface CardBagService {
    ResultVo<CardBag> addCardBag(CardBagVo cardBag, String cookie);

    ResultVo<String> delCardBag(Integer cardBagId, String cookie);

    ResultVo<CardBag> upDateCarBag(CardBagVo cardBag, Integer cardBagId, String cookie);

    List<Card> getCardBag(Integer cardBagId, String cookie, Integer pageSize, Integer pageNum);

    List<CardBag> getMyBag(String cookie, Integer pageSize, Integer pageNum);

    List<CardBag> search(String keyWord, String cookie, Integer pageSize, Integer pageNum);
    
}
