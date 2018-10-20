package com.example.springbootplay.controller.webservice;

import com.example.springbootplay.configuration.properties.CustomizeProperties;
import com.example.springbootplay.data.tabular.TestFileTabularData;
import com.example.springbootplay.data.dto.UserData;
import com.example.springbootplay.client.http.LocalhostClient;
import com.example.springbootplay.utils.TabularFileWriter;
import com.example.springbootplay.model.User;
import com.example.springbootplay.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Tony Ng on 3/10/2018.
 */
@Api(value = "/user", tags = "UserController")
@RequestMapping(value = "/user")
@RestController
public class UserController
{
	private Logger LOG = LogManager.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@Autowired
	private CustomizeProperties customizeProperties;

	@Autowired
	private LocalhostClient localhostClient;

	@ApiOperation(value = "getAllUser", notes = "Get All User")
	@GetMapping("")
	public List<User> getAllUser()
	{
		return userService.findAll();
	}

	@ApiOperation(value = "getUser", notes = "Get user")
	@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id)
	{
		LOG.info(String.format("getUser: %s", id));
		User u = userService.findById(id);
		return u;
	}

	@ApiOperation(value = "createUser", notes = "Create User")
	@ApiImplicitParam(name = "user", value = "User", required = true, dataType = "User")
	@PostMapping(value = "", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
	public UserData createUser(@RequestBody UserData userData)
	{
		User user = new User();
		user.setId(userData.getId());
		user.setName(userData.getName());
		user.setAge(userData.getAge());
		userService.insert(user);
		userService.findById(user.getId());
		return userData;
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
		userService.update(user);
		return userService.findById(user.getId());
	}

	@ApiOperation(value = "deleteUser", notes = "Delete User")
	@ApiImplicitParam(name = "user", value = "User", required = true, dataType = "User")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id)
	{
		userService.delete(id);
		return;
	}

	@GetMapping("helloWorld")
	public String helloWorld()
	{
		LOG.info("customizeProperties " + customizeProperties);

		return "Hellow World!";
	}

	@GetMapping("testClient")
	public String testClient()
	{
		LOG.info("customizeProperties " + customizeProperties);
		localhostClient.checkHealth();
		localhostClient.createUser();

		return "Hellow World!";
	}

	@GetMapping("testCSV")
	public void testCSV() throws Exception
	{
		List<TestFileTabularData> testFileTabularDatas = new ArrayList<>();
		TestFileTabularData testFileTabularData = new TestFileTabularData();
		testFileTabularData.setId("i\"d");
		testFileTabularData.setContent("cont,ent");
		testFileTabularData.setName("na\nme");
		testFileTabularDatas.add(testFileTabularData);

		TestFileTabularData testFileTabularData2 = new TestFileTabularData();
		testFileTabularData2.setId("a");
		testFileTabularData2.setContent("b");
		testFileTabularData2.setName("c");
		testFileTabularDatas.add(testFileTabularData2);
		String projectRoot = System.getProperty("user.dir");
		TabularFileWriter.writeCSV(projectRoot, "testFile", testFileTabularDatas);
		TabularFileWriter.writeTSV(projectRoot, "testFile", testFileTabularDatas);
	}
}