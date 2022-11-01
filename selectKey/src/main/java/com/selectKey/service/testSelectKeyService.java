package com.selectKey.service;

import com.selectKey.po.testSelectKeyAutoPo;
import com.selectKey.po.testSelectKeyUuidPo;

import java.util.List;

/**
 * @author jiangluhan
 * @Description:
 * @date 2022/3/2 16:05
 */

public interface testSelectKeyService {
    // 测selectKey标签的作用，当主键为自增时
    int testSelectKeyAuto(testSelectKeyAutoPo autoPo);

    // 测selectKey标签的作用，当主键为UUID时
    int testSelectKeyUuid(testSelectKeyUuidPo uuidPo);

    testSelectKeyAutoPo testList(List<Integer> list);
}
