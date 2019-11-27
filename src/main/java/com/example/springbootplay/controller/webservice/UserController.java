package com.example.springbootplay.controller.webservice;

import com.example.springbootplay.client.http.LocalhostClient;
import com.example.springbootplay.configuration.properties.CustomizeProperties;
import com.example.springbootplay.data.dto.UserData;
import com.example.springbootplay.data.tabular.TestFileTabularData;
import com.example.springbootplay.model.UserModel;
import com.example.springbootplay.service.UserService;
import com.example.springbootplay.utils.TabularFileUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
	public List<UserModel> getAllUser()
	{
		return userService.findAll();
	}

	@ApiOperation(value = "getUser", notes = "Get user")
	@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
	@GetMapping("/{id}")
	public UserModel getUser(@PathVariable Long id)
	{
		LOG.info(String.format("getUser: %s", id));
		UserModel u = userService.findById(id);
		return u;
	}

	@ApiOperation(value = "createUser", notes = "Create User")
	@ApiImplicitParam(name = "user", value = "User", required = true, dataType = "UserModel")
	@PostMapping(value = "", consumes = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_UTF8_VALUE }, produces = MediaType.APPLICATION_XML_VALUE)
	public UserData createUser(@RequestBody UserData userData)
	{
		UserModel userModel = new UserModel();
		userModel.setName(userData.getName());
		userModel.setAge(userData.getAge());
		userService.insert(userModel);
		UserModel userModel1 = userService.findById(userModel.getId());

		userData.setId(userModel1.getId());
		userData.setName(userModel1.getName());
		userData.setAge(userModel1.getAge());
		return userData;
	}

	@ApiOperation(value = "updateUser", notes = "Update User")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "User", required = true, dataType = "UserModel")
	})
	@PutMapping("/{id}")
	public UserModel updateUser(@PathVariable Long id, @RequestBody UserModel userModel)
	{
		userModel.setId(id);
		userService.update(userModel);
		return userService.findById(userModel.getId());
	}

	@ApiOperation(value = "deleteUser", notes = "Delete User")
	@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable Long id)
	{
		userService.delete(id);
		return;
	}

	@GetMapping("helloWorld")
	public String helloWorld(@RequestParam(required = true, value = "policy_number") String policyNumber) throws JsonProcessingException
	{
		UserData userData = new UserData();
		userData.setPurchaseDate(new Date());
		Map<String, String> insuranceProperty = new HashMap<>();
		insuranceProperty.put("INSURANCE_PLAN", "ZXS");
		userData.setInsuranceProperty(insuranceProperty);

		ObjectMapper objectMapper = new ObjectMapper();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ");
		objectMapper.setDateFormat(dateFormat);
		objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		String result = objectMapper.writeValueAsString(userData);
		LOG.info(result);

		return result;
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
		TabularFileUtils.writeCSV(projectRoot, "testFile", testFileTabularDatas);
		TabularFileUtils.writeTSV(projectRoot, "testFile", testFileTabularDatas);
	}

	@ApiOperation(value = "deleteUser", notes = "Delete User")
	@ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
	@PostMapping("/TravelCare/FWDIssuePolicy")
	public void postTest()
	{
		System.out.println("test timeout");
		return;
	}
}