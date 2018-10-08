package com.example.springbootplay.mapper;

import com.example.springbootplay.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by Tony Ng on 4/10/2018.
 * CURD can be generated using myBatis generator
 * check what annotation we have http://www.mybatis.org/mybatis-3/java-api.html#Mapper_Annotations
 */
@Mapper
@Service
public interface UserMapper
{
	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	long insert(User user);

	@Update("UPDATE USER SET age=#{age}, name=#{name} WHERE ID = #{id}")
	long update(User user);

	@Results(id = "userResult", value = {
			@Result(property = "id", column = "id", id = true),
			@Result(property = "name", column = "name"),
			@Result(property = "age", column = "age")
	})
	@Select("SELECT * FROM USER WHERE ID = #{id}")
	User findById(@Param("id") Long id);

	@Delete("DELETE FROM USER WHERE ID = #{id}")
	long delete(@Param("id") Long id);

	@Select("SELECT * FROM USER")
	List<User> findAll();

	@ResultMap("userResult")
	@Select("SELECT * FROM USER WHERE NAME = #{name}")
	User findByName(@Param("name") String name);
}