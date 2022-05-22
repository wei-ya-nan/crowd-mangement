package com.wyn.crowd.test;

import com.wyn.crowd.entity.po.MemberPO;
import com.wyn.crowd.entity.vo.PortalTypeVO;
import com.wyn.crowd.mapper.MemberPOMapper;
import com.wyn.crowd.mapper.ProjectPOMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/4/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class MyBatisTest {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberPOMapper memberPOMapper;

    @Autowired
    private ProjectPOMapper projectPOMapper;


    private Logger logger = LoggerFactory.getLogger(MyBatisTest.class);

    @Test
    public void test04(){
        List<PortalTypeVO> portalTypeVOS = projectPOMapper.selectPortalTypeVOList();
        for (PortalTypeVO portalTypeVO : portalTypeVOS) {
            System.out.println(portalTypeVO.toString());
        }

    }


    @Test
    public void test01() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    @Test
    public void test02() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123123");
        MemberPO memberPO = new MemberPO(null, "jack", encode, "jack", "jack@qq.com", 1, 1, "jack", "123123", 2);
        memberPOMapper.insert(memberPO);
    }

}
