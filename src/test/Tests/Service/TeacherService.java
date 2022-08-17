package Service;

import Entity.Teacher;

public interface TeacherService {
    void addTeacher(Teacher teacher);

    void delTeacher(String id);

    void updTeacher(Teacher teacher);

    Teacher getTeacher(String id);
}