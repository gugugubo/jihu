package com.gdut.jiyi.controller;

import com.gdut.jiyi.common.CommonPage;
import com.gdut.jiyi.common.ResultVo;
import com.gdut.jiyi.model.Card;
import com.gdut.jiyi.model.CardBag;
import com.gdut.jiyi.server.CardBagService;
import com.gdut.jiyi.util.CookieUtil;
import com.gdut.jiyi.util.ResultUtil;
import com.gdut.jiyi.vo.CardBagVo;
import com.gdut.jiyi.vo.CardVo;
import com.gdut.jiyi.vo.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

/**
 * @author 古春波
 * @description 卡包管理
 * @Date 2020/11/11 15:12
 * @version 1.0
 **/

@Validated
@RestController
@RequestMapping("/cardBag")
public class CardBagController {

    @Autowired
    private CardBagService cardBagService;


    /**
     * 新增卡包
     * @param cardBag 卡包
     * @param cookie cookie
     * @return 卡包
     */ 
    @PostMapping("/add")
    public ResultVo<CardBag> addCardBag(@Validated @RequestBody CardBagVo cardBag,
                                       @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return cardBagService.addCardBag(cardBag, cookie);
    }


    /**
     * 通过id删除卡包
     */
    @GetMapping("/delById")
    public ResultVo<String> delCardBag(@RequestParam Integer cardBagId,
                                     @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return cardBagService.delCardBag(cardBagId, cookie);
    }


    /**
     * 更新卡包信息
     */
    @PostMapping("/upDate")
    public ResultVo<CardBag> upDateCarBag(@Validated @RequestBody CardBagVo cardBag, Integer cardBagId,
                                     @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie){
        return cardBagService.upDateCarBag(cardBag, cardBagId, cookie);
    }


    /**
     * 分页获取卡包信息
     */
    @GetMapping("/get")
    public ResultVo<CommonPage<Card>> getCardBag(@RequestParam Integer cardBagId,
                                           @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie,
                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<Card>  cardList = cardBagService.getCardBag(cardBagId, cookie, pageSize,pageNum);
        return ResultUtil.successWithData(CommonPage.restPage(cardList));
    }


    /**
     * 分页获取自己的所有卡包
     */
    @GetMapping("/getMyBag")
    public ResultVo<CommonPage<CardBag>> getMyBag(@CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie,
                                                 @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<CardBag>  cardList = cardBagService.getMyBag(cookie, pageSize,pageNum);
        return ResultUtil.successWithData(CommonPage.restPage(cardList));
    }


    /**
     * 分页搜索从卡包描述或者卡包名字所有所有人的卡包
     */
    @GetMapping("/search")
    public ResultVo<CommonPage<CardBag>> search(@RequestParam String keyWord,
                                                  @CookieValue(CookieUtil.SESSION_COOKIE_NAME) String cookie,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        List<CardBag>  cardList = cardBagService.search(keyWord,cookie, pageSize,pageNum);
        return ResultUtil.successWithData(CommonPage.restPage(cardList));
    }
    

    /**
     * 获取默认卡包照片
     * @return 默认卡包照片
     */
    @GetMapping("/getDefaultPic")
    public ResultVo<List<Photo>> getDefaultPic(){
        String a=  "http://localhost:9090/build/20200818/cherries-2402449_640.jpg";
        String b=  "http://localhost:9090/build/20200818/fruit-2367029_640.jpg";
        String c = "http://localhost:9090/build/20200818/strawberry-2960533_640.jpg";
        String d = "http://localhost:9090/build/20200818/tomatoes-1280859_640.jpg";
        ArrayList<Photo> pics = new ArrayList<>();
        pics.add(new Photo("photo1",a));
        pics.add(new Photo("photo2",b));
        pics.add(new Photo("photo3",c));
        pics.add(new Photo("photo4",d));
        return ResultUtil.successWithData(pics);
    }
    
    
}
