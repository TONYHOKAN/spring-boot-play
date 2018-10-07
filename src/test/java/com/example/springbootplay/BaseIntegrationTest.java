package com.example.springbootplay;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Tony Ng on 7/10/2018.
 * Test no need web server (ServerServlet) should extend this
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("testing") // make sure the test will only run in correct profile
public abstract class BaseIntegrationTest
{
}
