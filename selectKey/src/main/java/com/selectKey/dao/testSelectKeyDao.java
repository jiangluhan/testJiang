package com.selectKey.dao;

import com.selectKey.po.testSelectKeyAutoPo;
import com.selectKey.po.testSelectKeyUuidPo;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/3/2 16:56
 */
public interface testSelectKeyDao {
    // 测selectKey标签的作用，当主键为自增时
    int testSelectKeyAuto(testSelectKeyAutoPo autoPo);

    // 测selectKey标签的作用，当主键为UUID时
    int testSelectKeyUuid(testSelectKeyUuidPo uuidPo);
}
