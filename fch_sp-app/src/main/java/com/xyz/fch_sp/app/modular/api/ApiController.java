/**
 * Copyright 2018-2020 stylefeng & fengshuonan (https://gitee.com/stylefeng)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.xyz.fch_sp.app.modular.api;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xyz.fch_sp.app.core.shiro.ShiroKit;
import com.xyz.fch_sp.app.core.shiro.ShiroUser;
import com.xyz.fch_sp.app.core.util.Encodes;
import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.core.util.JwtTokenUtil;
import com.xyz.fch_sp.app.modular.api.vo.ContractVo;
import com.xyz.fch_sp.app.modular.system.dao.UserMapper;
import com.xyz.fch_sp.app.modular.system.model.Contract;
import com.xyz.fch_sp.app.modular.system.model.Data;
import com.xyz.fch_sp.app.modular.system.model.Member;
import cn.stylefeng.roses.core.base.controller.BaseController;
import cn.stylefeng.roses.core.reqres.response.ErrorResponseData;
import com.xyz.fch_sp.app.modular.system.service.DataService;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wf.bitcoin.javabitcoindrpcclient.BitcoindRpcClient;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 接口控制器提供
 *
 * @author stylefeng
 * @Date 2018/7/20 23:39
 */
@RestController
@RequestMapping("/api")
@Slf4j
public class ApiController extends BaseController {


    @Autowired
    private DataService dataService;

    /**
     * 返回钱包内的UTXO
     * @param address
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("listUnspent")
    public JSONObject ListUnspent(String address) throws Exception {

        JSONObject json = new JSONObject();

        json = Api.ListUnspent(address);

        return json;

    }


    /**
     * 返回一个新的地址用于接收支付
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("getNewAddress")
    public String GetNewAddress() throws Exception {

        String json = Api.GetNewAddress();

        return json;

    }


    /**
     * 签名裸交易
     * @param hex
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("SignRawTransaction")
    public String SignRawTransaction(String hex) throws Exception {

        String json = Api.SignRawTransaction(hex);

        return json;

    }

    /**
     * 验证并发送裸交易到P2P网络
     * @param
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("SendRawTransaction")
    public String SendRawTransaction(@RequestBody JSONObject JSONObject) throws Exception {

        String json = Api.SendRawTransaction(JSONObject.getString("hex"));

        return json;

    }

    /**
     * 返回指定的裸交易
     * @param hex
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("GetRawTransaction")
    public JSONObject GetRawTransaction(String hex) throws Exception {

        JSONObject json = new JSONObject();

        json = Api.GetRawTransaction(hex);

        return json;

    }

    /**
     * 签名消息
     * @param address
     * @param message
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("SignMessage")
    public String SignMessage(String address, String message) throws Exception {

        String json = Api.SignMessage(address, message);

        return json;

    }

    /**
     * 验证签名的消息
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("VerifyMessage")
    public Boolean VerifyMessage(@RequestBody String jsons) throws Exception {

        JSONObject j = (JSONObject) JSONObject.parse(jsons);

        String d = Encodes.decodeBase64String(j.getString("data"));

        JSONObject data = (JSONObject) JSONObject.parse(d);

        Boolean json = Api.VerifyMessage(data.getString("address"), data.getString("sign"), data.getString("msg"));

        return json;

    }


    @ResponseBody
    @RequestMapping("GetBlock")
    public JSONObject GetBlock(String blockHash) throws Exception {

        BitcoindRpcClient.Block block = Api.GetBlock(blockHash);
        JSONObject ob = new JSONObject();

        ob.put("hash", block.hash());
        ob.put("confirmations", block.confirmations());
        ob.put("size", block.size());
        ob.put("height", block.height());
        ob.put("version", block.version());
        ob.put("merkleRoot", block.merkleRoot());
        ob.put("time", block.time());
        ob.put("bits", block.bits());
        ob.put("difficulty", block.difficulty());
        ob.put("previousHash", block.previousHash());
        ob.put("nextHash", block.nextHash());
        ob.put("chainwork", block.chainwork());
        ob.put("previous", block.previous());
        ob.put("next", block.next());
        ob.put("tx", block.tx());

        return ob;

    }

    @ResponseBody
    @RequestMapping("GetBlockHash")
    public String GetBlockHash(Integer height) throws Exception {
        String json = Api.GetBlockHash(height);
        return json;
    }


    @ResponseBody
    @RequestMapping("GetBlockCount")
    public Integer GetBlockCount() throws Exception {
        Integer json = Api.GetBlockCount();
        return json;
    }


}

