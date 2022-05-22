package com.wyn.crowd.service.api;

import com.wyn.crowd.entity.vo.DetailProjectVO;
import com.wyn.crowd.entity.vo.PortalTypeVO;
import com.wyn.crowd.entity.vo.ProjectVO;

import java.util.List;

/**
 * @author wei-ya-nan
 * @version 1.0
 * @date 2022/5/7
 */
public interface ProjectService {
    void saveProject(ProjectVO projectVO, Integer memberId);

    List<PortalTypeVO> getPortalTypeVO();

    DetailProjectVO getDetailProjectVO(Integer projectId);
}
