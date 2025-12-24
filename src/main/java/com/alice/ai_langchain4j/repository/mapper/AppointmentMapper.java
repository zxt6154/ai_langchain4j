package com.alice.ai_langchain4j.repository.mapper;

import com.alice.ai_langchain4j.repository.entity.Appointment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AppointmentMapper extends BaseMapper<Appointment> {
}
