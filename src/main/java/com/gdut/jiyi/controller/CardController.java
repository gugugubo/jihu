package com.gdut.jiyi.controller;

import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.CardBag;
import com.gdut.jiyi.server.CardBagService;
import com.gdut.jiyi.server.CardService;
import com.gdut.jiyi.util.CookieUtil;
import com.gdut.jiyi.vo.CardBagVo;
import com.gdut.jiyi.vo.CardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 古春波
 * @description 卡片管理
 * @Date 2020/11/11 15:13
 * @version 1.0
 **/
@Validated
@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;



    /**
     * 新增卡
     * @param card 卡
     * @param cookie cookie
     * @return 卡
     */
    @PostMapping("/add")
    public ResultVo<Card> addCard(@Validated @RequestBody CardVo card,
                                  @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return cardService.addCard(card, cookie);
    }

    /**
     * 通过id删除卡片
     */
    @PostMapping("/delById")
    public ResultVo<Integer> delCard(@RequestParam Integer cardId,
                                  @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return cardService.delCard(cardId, cookie);
    }


    /**
     * 更新卡片信息
     */
    @PostMapping("/upDate")
    public ResultVo<Card> upDateCard(@Validated @RequestBody CardVo card, Integer cardId,
                                     @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return cardService.upDateCard(card, cardId, cookie);
    }


}
