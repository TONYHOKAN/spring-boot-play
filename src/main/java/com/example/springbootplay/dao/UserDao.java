package com.example.springbootplay.dao;

import com.example.springbootplay.model.UserModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Tony Ng on 19/10/2018.
 */

@Repository
@Mapper
public interface UserDao extends BaseDao<UserModel>
{
	// demo of using own defined mapping
	@Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	long create(UserModel userModel);

//	@Results(id = "userResult", value = {
//			@Result(property = "id", column = "id", id = true),
//			@Result(property = "name", column = "name"),
//			@Result(property = "age", column = "age")
//	})
//	@Select("SELECT * FROM USER WHERE ID = #{id}")
	UserModel findById(@Param("id") Long id);

	@Select("SELECT * FROM USER")
	List<UserModel> findAll();

//	@ResultMap("userResult")
//	@Select("SELECT * FROM USER WHERE NAME = #{name}")
	UserModel findByName(@Param("name") String name);
}
