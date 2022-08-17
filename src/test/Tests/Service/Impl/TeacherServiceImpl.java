package Service.Impl;

import Entity.Teacher;
import Mapper.TeacherMapper;
import Service.TeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    TeacherMapper teacherMapper;

    @Override
    public void addTeacher(Teacher teacher) {
        boolean flag = this.save(teacher);
        if (flag){
            log.debug("保存老师成功！");
        }else {
            log.error("保存老师失败！");
        }

    }

    @Override
    public void delTeacher(String id) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        int deleteFlag = teacherMapper.delete(queryWrapper);
        if (deleteFlag==1){
            log.debug("删除老师成功！");
        }else {
            log.error("删除老师失败！");
        }
    }

    @Override
    public void updTeacher(Teacher teacher) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",teacher.getId());
        boolean updateFlag = this.update(teacher, queryWrapper);
        /*boolean updateFlag = this.update(teacher,new QueryWrapper<>());*/
        if (updateFlag){
            log.debug("修改老师成功！");
        }else {
            log.error("修改老师失败！");
        }
    }

    @Override
    public Teacher getTeacher(String id) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        return teacherMapper.selectOne(queryWrapper);
    }
}