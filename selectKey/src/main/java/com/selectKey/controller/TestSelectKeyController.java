package com.selectKey.controller;

import com.selectKey.po.testSelectKeyAutoPo;
import com.selectKey.po.testSelectKeyUuidPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 测试xxxx
     * @param autoPo
     */
    // 测selectKey标签的作用，当主键为自增时
    // 也就是说，因为id是自增的，如果我删除其中的某个数据，那我下一次插入的时候是从最后一个主键+1的位置开始，所以这个应该是获取最终的那个主键id，从下一个数据开始插入，并返回插入数据的id
    @RequestMapping(value = "/v1/testSelectKeyAuto", method = {RequestMethod.POST})
    public void testSelectKeyAuto(@RequestBody @Validated testSelectKeyAutoPo autoPo) {
        int insert = testSelectKeyService.testSelectKeyAuto(autoPo);
        if(insert != 1) {
            log.error("插入数据失败");
        }
        log.info("成功插入数据，且获取的autoId为：{}", autoPo.getId());
    }


    // 测selectKey标签的作用，当主键为uuid时
    // 经过测试发现，这里生成的uuid里面有符号“-”，一般来说是不能有"-"的，所以如果是uuid的话可能不用这种方式，直接用自定义的生成uuid文件
    @PostMapping("/v1/testSelectKeyUuid")
    public void testSelectKeyUuid(@RequestBody @Validated testSelectKeyUuidPo uuidPo) {
        int insert = testSelectKeyService.testSelectKeyUuid(uuidPo);
        if(insert != 1) {
            log.error("插入数据失败");
        }
        log.info("成功插入数据，且获取的uuid为：{}", uuidPo.getId());
    }

    /**
     * 测试foreach标签在inner join中使用是否生效
     */
    @GetMapping("/v1/testList")
    public void testList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(11);
        testSelectKeyAutoPo testSelectKeyAutoPo = testSelectKeyService.testList(list);
        log.info("成功获取数据：{}", testSelectKeyAutoPo);
    }

    @RequestMapping(value = "/testMethod", method = {RequestMethod.POST})
    public void testMethod(@RequestBody testSelectKeyUuidPo po) {
        System.out.println(po);
    }

    @GetMapping("/v1/testList")
    public void testListBatchInsert() {
//        List<GwMailServerInfoPo> list = new ArrayList<>();
//        GwMailServerInfoPo mailServerInfoPo = new GwMailServerInfoPo();
//        mailServerInfoPo.setTenantId("1");
//        list.add(mailServerInfoPo);
//        GwMailServerInfoPo mailServerInfoPo1 = new GwMailServerInfoPo();
//        mailServerInfoPo1.setTenantId("1");
//        list.add(mailServerInfoPo1);
//        mailServerService.insert(list);
////        mailServerService.insert(list.get(0));
//
//        list.forEach(po -> {
//            System.out.println(po.getId());
//        });




//<insert id="insert" useGeneratedKeys="true" keyProperty="id">
//                insert into gw_mail_server_info(tenant_id ,host, port, account, password, ssl_switch, create_time, update_time)
//        values
//                <foreach collection="list" item="item" separator=",">
//                (#{item.tenantId}, #{item.host}, #{item.port}, #{item.account}, #{item.password}, #{item.sslSwitch}, NOW(), NOW())
//        </foreach>
//    </insert>

        // 适配达梦和mysql批量插入获取批量插入后的ID，不能增加属性keyColumn="id"，如果加了只有mysql可以使用

    }

}
