package com.zylear.phone.grab;


import com.zylear.phone.grab.grab.impl.FeiNiuWangImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ComponentScan("com.zylear.phone.grab.*")
@SpringBootTest
public class PhoneGrabApplicationTests {

	@Test
	public void contextLoads() {
		FeiNiuWangImpl feiNiuWang=new FeiNiuWangImpl();
		feiNiuWang.odsPhoneInfoMapper.deleteBySource(feiNiuWang.thisSource);
		feiNiuWang.getWebInfo();
	}

}
