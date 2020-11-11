package com.gdut.jiyi.controller;

import com.gdut.jiyi.common.CommonPage;
import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.Collect;
import com.gdut.jiyi.server.CollectService;
import com.gdut.jiyi.util.CookieUtil;
import com.gdut.jiyi.util.ResultUtil;
import com.gdut.jiyi.vo.CardVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 古春波
 * @description 收藏管理
 * @Date 2020/11/11 15:14
 * @version 1.0
 **/
@Validated
@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;
    
    /**
     * 新增收藏
     */
    @PostMapping("/add")
    public ResultVo<Collect> addCard(@RequestParam("cardBagId") Integer cardBagId ,
                                  @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return collectService.addCard(cardBagId, cookie);
    }

    /**
     * 通过id删除
     */
    @PostMapping("/delById")
    public ResultVo<Integer> delCard(@RequestParam Integer cardBagId,
                                     @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return collectService.delCard(cardBagId, cookie);
    }


    /**
     * 分页获取收藏信息
     */
    @GetMapping("/get")
    public ResultVo<CommonPage<Collect>> getCardBag(@CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<Collect> cardList = collectService.getCardBag( cookie, pageSize,pageNum);
        return ResultUtil.successWithData(CommonPage.restPage(cardList));
    }

}
