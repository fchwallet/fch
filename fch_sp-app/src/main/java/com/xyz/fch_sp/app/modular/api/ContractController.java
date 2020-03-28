package com.xyz.fch_sp.app.modular.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xyz.fch_sp.app.core.common.exception.BizExceptionEnum;
import com.xyz.fch_sp.app.core.util.HttpUtils;
import com.xyz.fch_sp.app.core.util.JsonResult;
import com.xyz.fch_sp.app.core.util.UnicodeUtil;
import com.xyz.fch_sp.app.modular.api.vo.ContractVo;
import com.xyz.fch_sp.app.modular.system.model.Company;
import com.xyz.fch_sp.app.modular.system.model.Contract;
import com.xyz.fch_sp.app.modular.system.model.Member;
import com.xyz.fch_sp.app.modular.system.service.CompanyService;
import com.xyz.fch_sp.app.modular.system.service.ContractService;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/contract")
@Slf4j
public class ContractController {


    @Autowired
    private ContractService contractService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *
     * @param page              当前页
     * @param type              类型（1：我发送的 2：我接收的）
     * @param company           签约公司
     * @param status            状态
     * @param sendDate          发送日期
     * @param endDate           结束日期
     * @param overdueDate       过期时间
     * @param completeDate      完成时间
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public JsonResult contractList(Integer page, Integer type, String company, Integer status, String sendDate, String endDate, String overdueDate,
                                   String completeDate) {

        Member member = memberService.selectMemberInfo();
        String address = companyService.findAddressById(member.getId());

        page = page - 1;
        Integer limit = 10;
        Integer offset = page * limit;

        Map<String,Object> params = Maps.newHashMap();

        params.put("offset",offset);
        params.put("limit",limit);
        params.put("status", status);
        params.put("sendDate", sendDate);
        params.put("endDate", endDate);
        params.put("overdueDate", overdueDate);
        params.put("completeDate", completeDate);

        if (address == null) {
            params.put("sender", "");
            params.put("recipient", "");
        }

        if (type == 1) {
            params.put("sender",address);
            params.put("recipientCompany",company);
        } else if (type == 2) {
            params.put("recipient",address);
            params.put("senderCompany",company);
        }
        long total = contractService.pageCount(params);
        List<Contract> contractList = contractService.pageList(params);

        List<ContractVo> list = new ArrayList<>();

        for (Contract countract: contractList) {

            ContractVo contractVo = getContractVo(type, countract);

            list.add(contractVo);

        }

        long size;
        if (total % limit == 0){
            size = total/limit;
        } else {
            size = total/limit + 1;
        }

        return new JsonResult().addData("contracts",contractList).addData("total",String.valueOf(total)).addData("size",String.valueOf(size));
    }

    /**
     *
     * @param id
     * @param type
     * @return
     */
    @RequestMapping(value = "/findContractById", method = RequestMethod.POST)
    public JsonResult contractList(Integer id, Integer type) {

        memberService.selectMemberInfo();

        Contract countract = contractService.selectById(id);

        ContractVo ContractVo = getContractVo(type, countract);

        return new JsonResult().addData("contracts", ContractVo);

    }

    public ContractVo getContractVo(Integer type, Contract contract) {

        Company SenderCompany = companyService.findByAddress(contract.getSender());
        Company RecipientCompany = companyService.findByAddress(contract.getRecipient());
        contract.setRecipientCompany(RecipientCompany.getName());
        contract.setSenderCompany(SenderCompany.getName());

        ContractVo contractVo = null;
        if (type == 1) {

            contractVo = new ContractVo(contract.getId(), contract.getTitle(), contract.getRecipientCompany(), contract.getStatus(),
                    contract.getSendDate(), contract.getEndDate(), contract.getOverdueDate(),
                    contract.getCompleteDate(), contract.getMark(), contract.getTxid(), contract.getRecipient());

        } else if (type == 2) {

            contractVo = new ContractVo(contract.getId(), contract.getTitle(), contract.getSenderCompany(), contract.getStatus(),
                    contract.getSendDate(), contract.getEndDate(), contract.getOverdueDate(),
                    contract.getCompleteDate(), contract.getMark(), contract.getTxid(), contract.getSender());

        }

        return contractVo;
    }


//    @RequestMapping(value = "/dowloadContract", method = RequestMethod.POST)
//    public void dowloadContract(String txid, HttpServletResponse response) throws IOException {
//
//        Member member = memberService.selectMemberInfo();
//
//        String url = "http://120.27.232.146:8666/rest/Api/GetRawTransaction";
//        Map<String, String> query = new HashMap<>();
//        query.put("hex", txid);
//        JSONObject json = (JSONObject) JSONObject.parse(HttpUtils.postByHttpClient(url,query,"UTF-8"));
//        JSONArray vouts = json.getJSONArray("vout");
//        for (int i = 0; i < vouts.size(); i++) {
//            JSONObject ob = (JSONObject) vouts.get(i);
//            int n = ob.getInteger("n");
//            if (n == vouts.size() - 1) {
//                JSONObject scriptPubKey = ob.getJSONObject("scriptPubKey");
//                String op_return = scriptPubKey.getString("asm");
//                String op = op_return.replace("OP_RETURN", "");
//                String data = op.substring(op.indexOf("3c643e"), op.indexOf("3c2f643e")).replace("3c643e", "");
//                String title = op.substring(op.indexOf("3c743e"), op.indexOf("3c2f743e")).replace("3c743e", "");                          // 标题
//                byte[] bt = UnicodeUtil.hexStringToBytes(data);
//
//                String format;
//                data = data.toUpperCase();
//                if (data.contains("255044462D312E")) {
//                    format = ".pdf";
//                } else if (data.contains("504B0304")) {
//                    format = ".docx";
//                } else if (data.contains("D0CF11E0")) {
//                    format = ".doc";
//                }  else {
//                    format = ".txt";
//                }
//
//              /*  String path = "/java/electronic_signature/data/" + UnicodeUtil.hexStringToString(title) + format;
//                UnicodeUtil.byteTofile(bt, path);*/
//
//
//              response.setHeader("content-type", "application/octet-stream");
//              response.setContentType("application/octet-stream");
//              response.setHeader("Content-Disposition", "attachment;filename=" + UnicodeUtil.hexStringToString(title)+format);
//
//              byte[] buff = new byte[1024];
//              BufferedInputStream bufferedInputStream = null;
//              OutputStream outputStream = null;
//              try {
//                  outputStream = response.getOutputStream();
//
//                  bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(bt), bt.length);
//                  int num = bufferedInputStream.read(buff);
//                  while (num != -1) {
//                      outputStream.write(buff, 0, num);
//                      outputStream.flush();
//                      num = bufferedInputStream.read(buff);
//                  }
//              } catch (IOException e) {
//                  throw new RuntimeException(e.getMessage());
//              } finally {
//                  if (bufferedInputStream != null) {
//                      bufferedInputStream.close();
//                  }
//              }
//
//
//            }
//        }
//
//    }


    @RequestMapping(value = "/dowloadContract", method = RequestMethod.POST)
    public JsonResult dowloadContract(String txid, HttpServletResponse response) throws IOException {

        memberService.selectMemberInfo();

        String url = "http://120.27.232.146:8666/rest/Api/GetRawTransaction";
        Map<String, String> query = new HashMap<>();
        query.put("hex", txid);
        JSONObject json = (JSONObject) JSONObject.parse(HttpUtils.postByHttpClient(url,query,"UTF-8"));
        System.out.println(json);
        JSONArray vouts = json.getJSONArray("vout");
        for (int i = 0; i < vouts.size(); i++) {
            JSONObject ob = (JSONObject) vouts.get(i);
            int n = ob.getInteger("n");
            if (n == vouts.size() - 1) {
                JSONObject scriptPubKey = ob.getJSONObject("scriptPubKey");
                String op_return = scriptPubKey.getString("asm");
                String op = op_return.replace("OP_RETURN", "");
                String data = op.substring(op.indexOf("3c643e"), op.indexOf("3c2f643e")).replace("3c643e", "");
                String title = op.substring(op.indexOf("3c743e"), op.indexOf("3c2f743e")).replace("3c743e", "");                          // 标题

                return new JsonResult().addData("title", UnicodeUtil.hexStringToString(title)).addData("d", data);
            }
        }

        return  new JsonResult(BizExceptionEnum.SERVER_ERROR.getCode(),BizExceptionEnum.SERVER_ERROR.getMessage());

    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/setCallback", method = RequestMethod.POST)
    public JsonResult setCallback(String id, Integer status) {

        redisTemplate.opsForValue().set(id + "-callback" , status, 1000, TimeUnit.SECONDS);

        return new JsonResult();

    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/callback", method = RequestMethod.POST)
    public JsonResult callback(String id) {

        memberService.selectMemberInfo();

        Integer status = (Integer)redisTemplate.opsForValue().get(id + "-callback");

        return new JsonResult().addData("status", status != null ? status : 0);

    }


    @ResponseBody
    @RequestMapping(value = "/hex")
    public JsonResult hex(String id) {

        String hex = String.valueOf((Integer)redisTemplate.opsForValue().get(id));

        return new JsonResult().addData("hex", hex);

    }

    @ResponseBody
    @RequestMapping(value = "/getHex", method = RequestMethod.GET)
    public JsonResult getHex(String id) {

        String hex = String.valueOf(redisTemplate.opsForValue().get(id));

        return new JsonResult().addData("hex", hex);

    }

    @ResponseBody
    @RequestMapping(value="/sendHex", method = RequestMethod.POST)
    public JsonResult sendHex(String id, String hex) {

        redisTemplate.opsForValue().set(id, hex,900, TimeUnit.SECONDS);
        String a = "http://120.27.232.146:8422/api/contract/getHex?id="+id;

        return new JsonResult().addData("url", a);

    }


}
