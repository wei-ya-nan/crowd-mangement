package com.wyn.crowd.service.api;

import com.wyn.crowd.entity.po.MemberPO;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/5
 */
public interface MemberService {
    MemberPO getMemberPOByLoginAcct(String loginacct);

    void saveMember(MemberPO memberPO);
}
