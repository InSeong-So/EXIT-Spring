package day4.ems.member.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import day1.ems.member.dao.StudentDao;
import day1.ems.member.service.StudentAllSelectService;
import day1.ems.member.service.StudentDeleteService;
import day1.ems.member.service.StudentModifyService;
import day1.ems.member.service.StudentRegisterService;
import day1.ems.member.service.StudentSelectService;

// DB와 소통하는 설정
@Configuration
@Import({ MemberConfig2.class, MemberConfig3.class })
public class MemberConfig1 {

	// <bean id="studentDao" class="day1.ems.member.dao.StudentDao" ></bean>
	@Bean
	public StudentDao studentDao() {
		return new StudentDao();
	}

	/*
	 * 	<bean id="registerService" class="day1.ems.member.service.StudentRegisterService">
	 * 		<constructor-arg ref="studentDao" ></constructor-arg>
	 * 	</bean>
	 */
	@Bean
	public StudentRegisterService registerService() {
		return new StudentRegisterService(studentDao());
	}

	// 이하 위와 같음
	@Bean
	public StudentModifyService modifyService() {
		return new StudentModifyService(studentDao());
	}

	@Bean
	public StudentDeleteService deleteService() {
		return new StudentDeleteService(studentDao());
	}

	@Bean
	public StudentSelectService selectService() {
		return new StudentSelectService(studentDao());
	}

	@Bean
	public StudentAllSelectService allSelectService() {
		return new StudentAllSelectService(studentDao());
	}

}
