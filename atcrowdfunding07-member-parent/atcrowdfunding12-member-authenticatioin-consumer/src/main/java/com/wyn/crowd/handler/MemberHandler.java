package com.wyn.crowd.handler;

import com.wyn.crowd.api.MySQLRemoteService;
import com.wyn.crowd.api.RedisRemoteService;
import com.wyn.crowd.config.ShortMessageProperties;
import com.wyn.crowd.constant.CrowdConstant;
import com.wyn.crowd.entity.po.MemberPO;
import com.wyn.crowd.entity.vo.MemberLoginVO;
import com.wyn.crowd.entity.vo.MemberVO;
import com.wyn.crowd.util.ResultEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.concurrent.TimeUnit;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@Controller
public class MemberHandler {
    @Autowired
    private ShortMessageProperties shortMessageProperties;
    @Autowired
    private MySQLRemoteService mySQLRemoteService;
    @Autowired
    private RedisRemoteService redisRemoteService;

    @RequestMapping("/auth/do/member/register")
    public String register(MemberVO memberVO, ModelMap modelMap) {
        // 获取用户的手机号
        String phoneNum = memberVO.getPhoneNum();

        // 拼串
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

        // redis中获取数据
        ResultEntity<String> entity = redisRemoteService.getRedisStringValueByKeyRemote(key);
        String result = entity.getResult();
        if (ResultEntity.FAILED.equals(result)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, entity.getMessage());
            return "member-reg";
        }
        String redisCode = entity.getData();
        if (redisCode == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);
            return "member-reg";
        }
        // 查询到redis中的数据之后比较表单的验证码和Redis的验证码
        String fromCode = memberVO.getCode();
        if (!fromCode.equals(redisCode)) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_CODE_NOT_EXISTS);
            return "member-reg";
        }
        // 如果验证码一直的话就删除
        redisRemoteService.removeRedisKeyRemote(key);

        // 执行密码加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode(memberVO.getUserpswd());
        memberVO.setUserpswd(encode);

        // 执行保存 创建新的memberPo
        MemberPO memberPO = new MemberPO();
        // 复制属性
        BeanUtils.copyProperties(memberVO, memberPO);

        // 调用远程方法
        ResultEntity<String> saveMember = mySQLRemoteService.saveMember(memberPO);

        if (ResultEntity.FAILED.equals(saveMember.getResult())) {

            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, saveMember.getMessage());

            return "member-reg";
        }

        // 使用重定向避免刷新浏览器导致重新执行注册流程
        return "redirect:/auth/member/to/login/page";
    }


    @ResponseBody
    @RequestMapping("/auth/member/send/short/message.json")
    public ResultEntity<String> sendMessage(@RequestParam("phoneNum") String phoneNum) {
        //<editor-fold desc="Description">
        //        System.out.println(phoneNum);
        // 发送验证码的手机
//        ResultEntity<String> sendMessageResultEntity = CrowdUtil.sendCodeByShortMessage(
//                shortMessageProperties.getHost(),
//                shortMessageProperties.getPath(),
//                shortMessageProperties.getMethod(), phoneNum,
//                shortMessageProperties.getAppCode(),
//                shortMessageProperties.getSign(),
//                shortMessageProperties.getSkin());
//
//        // 判断短信的发送结果
//        if(ResultEntity.SUCCESS.equals(sendMessageResultEntity.getResult())){
//            // 发送成功存入到redis中
//            String code = sendMessageResultEntity.getData();
//
//            // 拼接存入key 的名称
//            String key = CrowdConstant.REDIS_CODE_PREFIX+phoneNum;
//
//            // 调用远程接口的方法
//            ResultEntity<String> stringResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key,
//                    code, 15, TimeUnit.MINUTES);
//
//            // 判断结果
//            if(ResultEntity.SUCCESS.equals(stringResultEntity.getResult())){
//                return ResultEntity.successWithData();
//            }else {
//                return stringResultEntity;
//            }
//
//        }else{
//            return sendMessageResultEntity;
//        }
        //</editor-fold>
        String key = CrowdConstant.REDIS_CODE_PREFIX + phoneNum;

        ResultEntity<String> stringResultEntity = redisRemoteService.setRedisKeyValueRemoteWithTimeout(key,
                phoneNum, 15, TimeUnit.MINUTES);
        return ResultEntity.successWithData();
    }

    @RequestMapping("/auth/member/do/login")
    public String Login(@RequestParam("loginacct") String loginacct,
                        @RequestParam("userpswd") String userpswd,
                        ModelMap modelMap,
                        HttpSession session) {
        // 调用远程 接口根据登录账号查询MemberPO对象
        ResultEntity<MemberPO> loginAcctRemote =
                mySQLRemoteService.getMemberPOByLoginAcctRemote(loginacct);
        if (ResultEntity.FAILED.equals(loginAcctRemote.getResult())) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, loginAcctRemote.getMessage());
            return "member-login";
        }
        MemberPO memberPO = loginAcctRemote.getData();
        if (memberPO == null) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }

        // 比较密码
        String memberPOUserpswd = memberPO.getUserpswd();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean matches = encoder.matches(userpswd, memberPOUserpswd);
        if (!matches) {
            modelMap.addAttribute(CrowdConstant.ATTR_NAME_MESSAGE, CrowdConstant.MESSAGE_LOGIN_FAILED);
            return "member-login";
        }
        // 创建MemberLoginVO对象存入Session域
        MemberLoginVO memberLoginVO = new MemberLoginVO(memberPO.getId(), memberPO.getUsername(),
                memberPO.getEmail());
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_MEMBER,memberLoginVO);
        return "redirect:/auth/member/to/center/page";

    }

    @RequestMapping("/auth/member/logout")
    public String logOut(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
