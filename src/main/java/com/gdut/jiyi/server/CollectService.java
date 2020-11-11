package com.gdut.jiyi.server;

import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.Collect;

import java.util.List;

/**
 * @author 古春波
 * @description 收藏管理
 * @Date 2020/11/11 15:14
 * @version 1.0
 **/
public interface CollectService {
    ResultVo<Collect> addCard(Integer cardBagId, String cookie);

    ResultVo<Integer> delCard(Integer cardBagId, String cookie);

    List<Collect> getCardBag(String cookie, Integer pageSize, Integer pageNum);
}
