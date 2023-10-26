package com.quartz.dynamic.state.dao;

import com.quartz.dynamic.state.entity.TaskInfo;
import com.quartz.dynamic.state.vo.req.TaskInfoReq;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TaskInfoDao {
    TaskInfo selectByJobName(String jobName);
    List<TaskInfo> selectAll();
    List<TaskInfo> selectTaskInfos(TaskInfoReq taskInfo);
    int deleteByPrimaryKey(Integer id);
    int insertSelective(TaskInfo record);
    TaskInfo selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(TaskInfo record);
}