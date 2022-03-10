package com.selectKey.service.impl;

import com.selectKey.po.testSelectKeyAutoPo;
import com.selectKey.po.testSelectKeyUuidPo;
import com.selectKey.service.testSelectKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/3/2 16:05
 */
@Service
@Slf4j
public class TestSelectKeyServiceImpl implements testSelectKeyService {

    @Autowired
    private com.selectKey.dao.testSelectKeyDao testSelectKeyDao;

    // 测selectKey标签的作用，当主键为自增时
    public int testSelectKeyAuto(testSelectKeyAutoPo po) {
        return testSelectKeyDao.testSelectKeyAuto(po);
    }

    // 测selectKey标签的作用，当主键为UUID时
    public int testSelectKeyUuid(testSelectKeyUuidPo uuidPo) {
        return testSelectKeyDao.testSelectKeyUuid(uuidPo);
    }
}
