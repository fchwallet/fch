package com.xyz.fch_sp.app.modular.api;


import cn.stylefeng.roses.core.base.controller.BaseController;
import com.alibaba.fastjson.JSONObject;
import com.xyz.fch_sp.app.core.common.exception.BizExceptionEnum;
import com.xyz.fch_sp.app.core.mail.MailService;
import com.xyz.fch_sp.app.core.shiro.ShiroKit;
import com.xyz.fch_sp.app.core.util.*;
import com.xyz.fch_sp.app.modular.system.model.Member;
import com.xyz.fch_sp.app.modular.system.service.CompanyService;
import com.xyz.fch_sp.app.modular.system.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;


@RestController
@RequestMapping("/api/member")
@Slf4j
public class MemberController extends BaseController {

    @Autowired
    private MailService mailService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CompanyService companyService;

    //发邮件
    @RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
    public JsonResult emailRegistration(@RequestBody String to) {

        String code = RandomUtil.getRandomString(10);

        String subject = "VNS注册";
        StringBuffer sb = new StringBuffer();
        sb.append("<p>你好，感谢您注册vnsblock.com，我们注意到您尚未验证您的电子邮件，请通过验证您的电子邮件来完成帐户设置。只需点击下面的“确认”链接。确认链接：</p>")
                .append("<a href='http://www.vnsblock.com/email="+to+"&code="+code+"' target='view_window'>http://www.vnsblock.com/email="+to+"&code="+code+"</a>");
        String text = sb.toString();

        mailService.snedAli(to,subject,text);

        redisTemplate.opsForValue().set(to,code);

        return new JsonResult().addData("code",0);
    }


    // 激活账号
    @RequestMapping(value = "/activation/user", method = RequestMethod.POST)
    public JsonResult activationUser(String email, String code) {

        Member member = memberService.selectEmail(email);

        if (member != null && member.getStatus() == 1) {
            return new JsonResult().addData("10003", "邮箱已激活！");
        }

        String emailCode = (String)redisTemplate.opsForValue().get(email);

        if (emailCode.equals(code)) {

            Member memberInfo = new Member();
            memberInfo.setStatus(1);
            memberInfo.setId(member.getId());
            memberService.updateMembers(memberInfo);

        }


        return new JsonResult().addData("code",0);
    }

    // 个人中心
    @RequestMapping(value = "/personalCenter", method = RequestMethod.POST)
    public JsonResult personalCenter(HttpServletRequest request) {

        String subjet = JwtTokenUtil.getSubjectFromRequest(request);

        return new JsonResult().addData("code",0);
    }


    /**
     * 发送短信
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    public JsonResult sendSms(String phone) {

        try {

            int radomInt = new Random().nextInt(999999);

            redisTemplate.opsForValue().set(phone, radomInt, 1000, TimeUnit.SECONDS);

            SmsSendUtil.send(phone, String.valueOf(radomInt));

            return new JsonResult();

        } catch(Exception e) {
            e.printStackTrace();
            return new JsonResult(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
        }

    }

    /**
     *  账号注册
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/register", method = RequestMethod.POST)
    public JsonResult register(String username, String password, String code){

        Member userName = memberService.selectMembers(username);

        if (userName != null && !"".equals(userName.getUsername())) {
            return new JsonResult(BizExceptionEnum.USER_REGISTER_REPEAT_ERROR.getCode(), BizExceptionEnum.USER_REGISTER_REPEAT_ERROR.getMessage());
        }

        String msg = String.valueOf((Integer)redisTemplate.opsForValue().get(username));

        if (msg != null && !"".equals(msg)) {

            if (!msg.equals(code)) {
                return new JsonResult(BizExceptionEnum.USER_REGISTER_CODE_ERROR.getCode(),BizExceptionEnum.USER_REGISTER_CODE_ERROR.getMessage());
            }

        } else {
            return new JsonResult(BizExceptionEnum.USER_REGISTER_OVERDUE_ERROR.getCode(),BizExceptionEnum.USER_REGISTER_OVERDUE_ERROR.getMessage());
        }

        Member memberInfo = new Member();
        memberInfo.setUsername(username);
        String salt = RandomUtil.getRandomString(4);
        memberInfo.setPassword(ShiroKit.md5(password, salt));
        memberInfo.setSalt(salt);
        memberInfo.setStatus(0);
        memberInfo.setCreateDate(new Date());

        memberService.insertMembers(memberInfo);

        return new JsonResult();

    }

    /**
     * 登录
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JsonResult login() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();

        Member info = memberService.selectMembers(username);
        String ps = ShiroKit.md5(password, info.getSalt());

        Member member = memberService.findMember(username, ps);

        if (member != null) {
            return new JsonResult().addData("token",JwtToken.sign(member.getUsername(), member.getPassword()));
        }

        return new JsonResult(BizExceptionEnum.USER_NAME_PASSWORD_ERROR.getCode(),BizExceptionEnum.USER_NAME_PASSWORD_ERROR.getMessage());

    }

    /**
     * 修改密码
     * @param password
     * @param newPassword
     * @return
     */
    @RequestMapping(value = "/modifyPassword", method = RequestMethod.POST)
    public JsonResult modifyPassword(String password, String newPassword) {

        Member member = memberService.selectMemberInfo();

        try {

            String afterSecretPassword = ShiroKit.md5(password, member.getSalt());

            if (!member.getPassword().equals(afterSecretPassword)) {
                return new JsonResult(BizExceptionEnum.USER_PASSWORD_ERROR.getCode(), BizExceptionEnum.USER_PASSWORD_ERROR.getMessage());
            }

            Member update = new Member();
            String salt = RandomUtil.getRandomString(4);
            update.setPassword(ShiroKit.md5(newPassword, salt));
            update.setSalt(salt);
            update.setId(member.getId());
            memberService.updateMembers(update);


        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(BizExceptionEnum.SERVER_ERROR.getCode(), BizExceptionEnum.SERVER_ERROR.getMessage());
        }

        return new JsonResult();
    }

    /**
     * 查询用户地址
     * @return
     */
    @RequestMapping(value = "/findMyAddress", method = RequestMethod.POST)
    public JsonResult findMyAddress() {

        Member member = memberService.selectMemberInfo();

        String address = companyService.findAddressById(member.getId());

        return new JsonResult().addData("address", address);

    }

}
