package com.gdut.jiyi.server.impl;

import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.common.ServiceException;
import com.gdut.jiyi.mapper.CollectMapper;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.CardExample;
import com.gdut.jiyi.model.Collect;
import com.gdut.jiyi.model.CollectExample;
import com.gdut.jiyi.server.CollectService;
import com.gdut.jiyi.util.RedisUtil;
import com.gdut.jiyi.util.RegUtil;
import com.gdut.jiyi.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 古春波
 * @description 收藏管理
 * @Date 2020/11/11 15:15
 * @version 1.0
 **/
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;


    @Override
    public ResultVo<Collect> addCard(Integer cardBagId, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        Collect collect = new Collect();
        collect.setCardBagId(cardBagId);
        collect.setCollectionTime(new Date());
        collect.setUserId(Integer.valueOf(userId));
        collectMapper.insert(collect);
        return ResultUtil.successWithData(collect);
    }

    @Override
    public ResultVo<Integer> delCard(Integer cardBagId, String cookie) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        CollectExample collectExample = new CollectExample();
        collectExample.createCriteria().andCardBagIdEqualTo(cardBagId);
        int i = collectMapper.deleteByExample(collectExample);
        return ResultUtil.successWithData(i);
    }

    @Override
    public List<Collect> getCardBag(String cookie, Integer pageSize, Integer pageNum) {
        String userId = RedisUtil.getSessionUserId(hashOperations, cookie);
        if (RegUtil.isEmpty(userId)){
            throw new ServiceException("cookie失效");
        }
        PageHelper.startPage(pageNum, pageSize);
        CollectExample collectExample = new CollectExample();
        return collectMapper.selectByExample(collectExample);
    }
}
