package com.test;

import com.service.EhcacheService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = {"classpath:conf/spring/*"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringTestCase extends AbstractJUnit4SpringContextTests {
}
