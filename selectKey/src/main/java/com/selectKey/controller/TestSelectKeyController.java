package com.selectKey.controller;

import com.selectKey.po.testSelectKeyAutoPo;
import com.selectKey.po.testSelectKeyUuidPo;
import com.selectKey.service.testSelectKeyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author jiangluhan
 * @Description: test SelectKey[1、AUTO 2、UUID]
 * @date 2022/3/2 15:55
 */
@RestController
@RequestMapping("/testSelectKey")
@Slf4j
public class TestSelectKeyController {

    @Autowired
    private com.selectKey.service.testSelectKeyService testSelectKeyService;


    // 测selectKey标签的作用，当主键为自增时
    // 也就是说，因为id是自增的，如果我删除其中的某个数据，那我下一次插入的时候是从最后一个主键+1的位置开始，所以这个应该是获取最终的那个主键id，从下一个数据开始插入，并返回插入数据的id
    @GetMapping("/v1/testSelectKeyAuto")
    public void testSelectKeyAuto(@RequestBody @Validated testSelectKeyAutoPo autoPo) {
        int insert = testSelectKeyService.testSelectKeyAuto(autoPo);
        if(insert != 1) {
            log.error("插入数据失败");
        }
        log.info("成功插入数据，且获取的autoId为：{}", autoPo.getId());
    }


    // 测selectKey标签的作用，当主键为uuid时
    // 经过测试发现，这里生成的uuid里面有符号“-”，一般来说是不能有"-"的，所以如果是uuid的话可能不用这种方式，直接用自定义的生成uuid文件
    @GetMapping("/v1/testSelectKeyUuid")
    public void testSelectKeyUuid(@RequestBody @Validated testSelectKeyUuidPo uuidPo) {
        int insert = testSelectKeyService.testSelectKeyUuid(uuidPo);
        if(insert != 1) {
            log.error("插入数据失败");
        }
        log.info("成功插入数据，且获取的uuid为：{}", uuidPo.getId());
    }
}
