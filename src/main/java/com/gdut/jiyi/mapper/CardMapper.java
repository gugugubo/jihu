package com.gdut.jiyi.mapper;

import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.CardExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CardMapper {
    long countByExample(CardExample example);

    int deleteByExample(CardExample example);

    int deleteByPrimaryKey(Integer cardId);

    int insert(Card record);

    int insertSelective(Card record);

    List<Card> selectByExampleWithBLOBs(CardExample example);

    List<Card> selectByExample(CardExample example);

    Card selectByPrimaryKey(Integer cardId);

    int updateByExampleSelective(@Param("record") Card record, @Param("example") CardExample example);

    int updateByExampleWithBLOBs(@Param("record") Card record, @Param("example") CardExample example);

    int updateByExample(@Param("record") Card record, @Param("example") CardExample example);

    int updateByPrimaryKeySelective(Card record);

    int updateByPrimaryKeyWithBLOBs(Card record);

    int updateByPrimaryKey(Card record);
}