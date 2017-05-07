package edu.awt.springmvc.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import edu.awt.springmvc.bean.Course;
import edu.awt.springmvc.bean.Registration;
import edu.awt.springmvc.bean.Student;

@Service
public class CRService {
	
	@Autowired
	JdbcTemplate db;
	
	public Student getStudent(int regno){
		String sql = "select * from student where regno = ?";
		return db.queryForObject(sql,new Object[]{regno},new BeanPropertyRowMapper<Student>(Student.class));
	}
	
	public Course getCourse(String code){
		String sql = "select * from course where code like '?'";
		return db.queryForObject(sql,new Object[]{code},new BeanPropertyRowMapper<Course>(Course.class));
	}
	
	public List<Registration> getReg(int regno){
		String sql = "select code,coursename from course where code in (select code from registration where regno =?)";
		return db.query(sql, new Object[]{regno},new BeanPropertyRowMapper<Registration>(Registration.class));
	}
	
	public void Register(int regno,String code){
		String sql = "Insert into registration(regno,code) values (?,?)";
		db.update(sql,new Object[]{regno,code});
	}
	
	public List<Course> offeredCourses(int regno){
		String sql = "select c.* from course c where code not in (select code from registration where regno = ?)";
		return db.query(sql, new Object[]{regno}, new BeanPropertyRowMapper<Course>(Course.class));
	}
	
	public void Withdraw(int regno,String code){
		String sql = "delete from registration where regno = ? and code = ?";
		db.update(sql,new Object[]{regno,code});
	}
}
