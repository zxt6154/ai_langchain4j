package com.alice.ai_langchain4j.service;

import com.alice.ai_langchain4j.repository.entity.Appointment;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AppointmentService extends IService<Appointment> {
    Appointment getOne(Appointment appointment);
}