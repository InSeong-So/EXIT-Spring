package day4.ems.member.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import day1.ems.member.DataBaseConnectionInfo;

// 개발용, 서비스용 DB 설정파일
@Configuration
public class MemberConfig2 {

	// 리스트 형태로 프로퍼티를 설정하는 방법
	/*<bean id="dataBaseConnectionInfoDev" class="day1.ems.member.DataBaseConnectionInfo">
		<property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
		<property name="userId" value="scott" />
		<property name="userPw" value="tiger" />
	</bean>*/
	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoDev() {
		// DB는 객체를 새로 생성해줘야 함
		DataBaseConnectionInfo infoDev = new DataBaseConnectionInfo();
		infoDev.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		infoDev.setUserId("scott");
		infoDev.setUserPw("tiger");

		return infoDev;
	}

	@Bean
	public DataBaseConnectionInfo dataBaseConnectionInfoReal() {
		DataBaseConnectionInfo infoReal = new DataBaseConnectionInfo();
		infoReal.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		infoReal.setUserId("scott");
		infoReal.setUserPw("tiger");

		return infoReal;
	}
}
