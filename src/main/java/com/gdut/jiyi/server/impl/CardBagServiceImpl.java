package com.gdut.jiyi.server.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.common.ServiceException;
import com.gdut.jiyi.mapper.CardBagMapper;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.CardBag;
import com.gdut.jiyi.model.CardBagExample;
import com.gdut.jiyi.server.CardBagService;
import com.gdut.jiyi.server.CardService;
import com.gdut.jiyi.server.CollectService;
import com.gdut.jiyi.util.RedisUtil;
import com.gdut.jiyi.util.RegUtil;
import com.gdut.jiyi.util.ResultUtil;
import com.gdut.jiyi.vo.CardBagVo;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 古春波
 * @description 卡包管理
 * @Date 2020/11/11 15:13
 * @version 1.0
 **/
@Service
@Slf4j
public class CardBagServiceImpl implements CardBagService {
    
    @Autowired
    private CardService cardService;
    
    @Autowired
    private CardBagMapper cardBagMapper;
    
    @Autowired
    private CollectService collectService;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    
    @Override
    public ResultVo<CardBag> addCardBag(CardBagVo cardBag, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        CardBag cardBagI = new CardBag();
        cardBagI.setUserId(Integer.valueOf(userId));
        BeanUtil.copyProperties(cardBag, cardBagI);
        cardBagMapper.insertSelective(cardBagI);
        return ResultUtil.successWithData(cardBagI);
    }

    @Override
    @Transactional
    public ResultVo<String> delCardBag(Integer cardBagId, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        cardBagMapper.deleteByPrimaryKey(cardBagId);
        cardService.delCardByBagId(cardBagId);
        collectService.delCard(cardBagId, cookie);
        return ResultUtil.success();
    }

    @Override
    public ResultVo<CardBag> upDateCarBag(CardBagVo cardBag, Integer cardBagId, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        CardBag cardBagIn = new CardBag();
        cardBagIn.setCardBagId(cardBagId);
        BeanUtil.copyProperties(cardBag, cardBagIn);
        cardBagMapper.updateByPrimaryKeySelective(cardBagIn);
        return ResultUtil.successWithData(cardBagIn);
    }

    @Override
    public List<Card> getCardBag(Integer cardBagId, String cookie, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Card> cards= cardService.getByCardBagId(cardBagId ,pageSize , pageNum);
        return cards;
    }

    @Override
    public List<CardBag> getMyBag(String cookie, Integer pageSize, Integer pageNum) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        PageHelper.startPage(pageNum, pageSize);
        CardBagExample cardBagExample = new CardBagExample();
        cardBagExample.createCriteria().andUserIdEqualTo(Integer.valueOf(userId));
        return cardBagMapper.selectByExample(cardBagExample);
    }

    @Override
    public List<CardBag> search(String keyWord, String cookie, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        CardBagExample cardBagExample = new CardBagExample();
        CardBagExample.Criteria criteria = cardBagExample.createCriteria();
        if(StrUtil.isNotEmpty(keyWord)){
            criteria.andNameLike('%'+keyWord+'%');
            cardBagExample.or(cardBagExample.createCriteria().andInfoLike('%'+keyWord+'%'));
        }
        return cardBagMapper.selectByExample(cardBagExample);
    }
}
