package com.wyn.crowd.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/6
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO implements Serializable {

    private String loginacct;

    private String userpswd;

    private String username;

    private String email;

    private String phoneNum;

    private String code;

}
