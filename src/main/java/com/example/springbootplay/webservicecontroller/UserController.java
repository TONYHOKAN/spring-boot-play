package com.example.springbootplay.webservicecontroller;

import com.example.springbootplay.mapper.UserMapper;
import com.example.springbootplay.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by Tony Ng on 3/10/2018.
 */
@Api(value = "/user", tags = "UserController")
@RequestMapping(value="/user")
@RestController
public class UserController
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserMapper userMapper;

	@ApiOperation(value = "getAllUser", notes = "Get All User")
	@GetMapping("")
	public List<User> getAllUser()
	{
		return userMapper.findAll();
	}

	@ApiOperation(value = "getUser", notes = "Get user")
	@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id)
	{
		logger.info(String.format("getUser: %s", id));
		User u = userMapper.findById(id);
		return u;
	}

	@ApiOperation(value = "createUser", notes = "Create User")
	@ApiImplicitParam(name = "user", value = "User", required = true, dataType = "User")
	@PostMapping("")
	public User createUser(@RequestBody User user)
	{
		long newObjectId = userMapper.insert(user);
		return userMapper.findById(newObjectId);
	}

	@ApiOperation(value = "updateUser", notes = "Update User")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "User", required = true, dataType = "User")
	})
	@PutMapping("/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User user)
	{
		user.setId(id);
		long newObjectId = userMapper.update(user);
		return userMapper.findById(newObjectId);
	}

	@ApiOperation(value = "deleteUser", notes = "Delete User")
	@ApiImplicitParam(name = "user", value = "User", required = true, dataType = "User")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id)
	{
		userMapper.delete(id);
		return;
	}
}