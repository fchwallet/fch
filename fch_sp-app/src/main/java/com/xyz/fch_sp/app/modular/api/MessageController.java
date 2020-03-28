package com.xyz.fch_sp.app.modular.api;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

    @RequestMapping(value = "/error")
    @ResponseBody
    public JSONObject error(String message) {
        JSONObject reuslt = new JSONObject();
        reuslt.put("code", 400401);
        reuslt.put("msg", "该用户token失效，请重新登录");
        return reuslt;
    }

}
