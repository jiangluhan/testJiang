package com.selectKey.service;

import com.selectKey.po.testSelectKeyAutoPo;
import com.selectKey.po.testSelectKeyUuidPo;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/3/2 16:05
 */

public interface testSelectKeyService {
    // 测selectKey标签的作用，当主键为自增时
    public int testSelectKeyAuto(testSelectKeyAutoPo autoPo);

    // 测selectKey标签的作用，当主键为UUID时
    public int testSelectKeyUuid(testSelectKeyUuidPo uuidPo);
}
