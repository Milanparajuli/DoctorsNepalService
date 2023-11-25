package com.milan.doctorsNepal.mapper;

import com.milan.doctorsNepal.dto.AppointmentDto;
import com.milan.doctorsNepal.entity.Appointment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = BaseMapper.SPRING_MODEL)
public abstract class AppointmentMapper extends BaseMapper<Appointment, AppointmentDto> {

}
