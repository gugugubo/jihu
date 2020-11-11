package com.gdut.jiyi.mapper;

import com.gdut.jiyi.model.CardBag;
import com.gdut.jiyi.model.CardBagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardBagMapper {
    long countByExample(CardBagExample example);

    int deleteByExample(CardBagExample example);

    int deleteByPrimaryKey(Integer cardBagId);

    int insert(CardBag record);

    int insertSelective(CardBag record);

    List<CardBag> selectByExample(CardBagExample example);

    CardBag selectByPrimaryKey(Integer cardBagId);

    int updateByExampleSelective(@Param("record") CardBag record, @Param("example") CardBagExample example);

    int updateByExample(@Param("record") CardBag record, @Param("example") CardBagExample example);

    int updateByPrimaryKeySelective(CardBag record);

    int updateByPrimaryKey(CardBag record);
}