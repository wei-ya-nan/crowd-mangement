package com.wyn.crowd.service.impl;

import com.wyn.crowd.entity.po.MemberPO;
import com.wyn.crowd.entity.po.MemberPOExample;
import com.wyn.crowd.mapper.MemberPOMapper;
import com.wyn.crowd.mapper.TagPOMapper;
import com.wyn.crowd.service.api.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/5
 */
@Transactional(readOnly = true)
@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberPOMapper memberPOMapper;



    public MemberPO getMemberPOByLoginAcct(String loginacct) {
        MemberPOExample example = new MemberPOExample();
        MemberPOExample.Criteria criteria = example.createCriteria();
        criteria.andLoginacctEqualTo(loginacct);
        List<MemberPO> memberPOS = memberPOMapper.selectByExample(example);
        return memberPOS.get(0);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW,
            rollbackFor = Exception.class,
            readOnly = false)
    public void saveMember(MemberPO memberPO) {
        memberPOMapper.insertSelective(memberPO);
    }
}
