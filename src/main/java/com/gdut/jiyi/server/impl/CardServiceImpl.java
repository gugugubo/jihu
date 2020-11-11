package com.gdut.jiyi.server.impl;

import cn.hutool.core.bean.BeanUtil;
import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.common.ServiceException;
import com.gdut.jiyi.mapper.CardMapper;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.CardExample;
import com.gdut.jiyi.server.CardService;
import com.gdut.jiyi.util.RedisUtil;
import com.gdut.jiyi.util.RegUtil;
import com.gdut.jiyi.util.ResultUtil;
import com.gdut.jiyi.vo.CardVo;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 古春波
 * @description 卡片管理
 * @Date 2020/11/11 15:13
 * @version 1.0
 **/
@Service
@Slf4j
public class CardServiceImpl implements CardService {
    
    @Autowired
    private CardMapper cardMapper;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;


    @Override
    public ResultVo<Card> addCard(CardVo card, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        Card cardIn = new Card();
        BeanUtil.copyProperties(card, cardIn);
        cardMapper.insertSelective(cardIn);
        return ResultUtil.successWithData(cardIn);
    }

    @Override
    public ResultVo<Integer> delCard(Integer cardId, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        int i = cardMapper.deleteByPrimaryKey(cardId);
        return ResultUtil.successWithData(i);
    }

    @Override
    public Integer delCardByBagId(Integer cardBagId) {
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andCardBagIdEqualTo(cardBagId);
        return cardMapper.deleteByExample(cardExample);
    }

    @Override
    public ResultVo<Card> upDateCard(CardVo card, Integer cardId, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        Card cardIn = new Card();
        cardIn.setCardId(cardId);
        BeanUtil.copyProperties(card, cardIn);
        cardMapper.updateByPrimaryKeySelective(cardIn);
        return ResultUtil.successWithData(cardIn);
    }

    @Override
    public List<Card> getByCardBagId(Integer cardBagId, Integer pageSize, Integer pageNum) {
      
        CardExample cardExample = new CardExample();
        cardExample.createCriteria().andCardBagIdEqualTo(cardBagId);
        return cardMapper.selectByExampleWithBLOBs(cardExample);
    }


}
