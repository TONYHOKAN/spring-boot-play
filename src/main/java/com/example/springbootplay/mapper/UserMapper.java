package com.example.springbootplay.mapper;

import com.example.springbootplay.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


/**
 * Created by Tony Ng on 4/10/2018.
 */
@Mapper
@Service
public interface UserMapper
{
	@Select("SELECT * FROM USER WHERE NAME = #{name}")
	User findByName(@Param("name") String name);

	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
	int insert(@Param("name") String name, @Param("age") Integer age);

}