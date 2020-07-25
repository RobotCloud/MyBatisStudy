package com.robot.dao;

import com.robot.pojo.Teacher;
import org.apache.ibatis.annotations.Param;

public interface TeacherMapper {

    Teacher getTeacher(@Param("tid") int id);
}
