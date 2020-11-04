package com.lkl.controller.common;

import com.lkl.utils.IdCardUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 刘奎亮
 * @date 2020/10/21
 */
@Slf4j
@Api(tags = "第三方相关接口")
@RestController
@RequestMapping("/common")
public class ThirdPartyController {

    @ApiOperation("身份证信息")
    @RequestMapping(value = "/token/idcard", method = {RequestMethod.POST, RequestMethod.GET})
    public String getToken(String idcard) {
        String cardToken = "";
        try {
            cardToken = IdCardUtil.getCardToken(idcard);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cardToken;
    }
}