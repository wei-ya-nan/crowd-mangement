package com.wyn.crowd.api;

import com.wyn.crowd.entity.po.MemberPO;
import com.wyn.crowd.entity.vo.PortalTypeVO;
import com.wyn.crowd.entity.vo.ProjectVO;
import com.wyn.crowd.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/4/4
 */
@FeignClient("wyn-crowd-mysql")
public interface MySQLRemoteService {
    // 实体类要传输数据就要加上RequestBody
    @RequestMapping("/get/memberpo/by/login/acct/remote")
    ResultEntity<MemberPO> getMemberPOByLoginAcctRemote(@RequestParam("loginacct")String loginacct);

    @RequestMapping("/save/member/remote")
    public ResultEntity<String> saveMember(@RequestBody MemberPO memberPO);

    @RequestMapping("/save/project/vo/remote")
    ResultEntity<String> saveProjectVORemote(@RequestBody ProjectVO projectVO, @RequestParam("id") Integer id);

    @RequestMapping("/get/portal/type/project/data/remote")
    public ResultEntity<List<PortalTypeVO>> getPortalTypeProjectDataRemote();
}
