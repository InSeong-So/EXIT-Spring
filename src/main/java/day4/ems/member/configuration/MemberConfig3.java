package day4.ems.member.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import day1.ems.member.DataBaseConnectionInfo;
import day1.ems.member.service.EMSInformationService;

// 유틸 설정파일
@Configuration
public class MemberConfig3 {

	/*
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
	*/
	// 위의 내용을 분리할 경우 참조가 되지 않아 예외가 발생한다.
	// 해결방법은? 의존성 주입을 통해 객체를 참조하게 한다.
	@Autowired
	DataBaseConnectionInfo dataBaseConnectionInfoDev;

	@Autowired
	DataBaseConnectionInfo dataBaseConnectionInfoReal;

	// 뒤에 선언된 필드값은 참조하는 객체의 메소드값을 적는것이므로 같은 객체라 혼란스러워하지 않아도 된다.

	@Bean
	public EMSInformationService informationService() {
		EMSInformationService info = new EMSInformationService();

		// 프로퍼티가 하나인 경우는 아래와 같이 작성
		info.setInfo("Education Management System program was developed in 2015.");
		info.setCopyRight("COPYRIGHT(C) 2015 EMS CO., LTD. ALL RIGHT RESERVED. CONTACT MASTER FOR MORE INFORMATION.");
		info.setVer("The version is 1.0");
		info.setsYear(2015);
		info.setsMonth(1);
		info.setsDay(1);
		info.seteYear(2015);
		info.seteMonth(2);
		info.seteDay(28);

		// 리스트 형태의 프로퍼티 설정 = ArrayList 사용
		ArrayList<String> developers = new ArrayList<String>();
		developers.add("Cheney.");
		developers.add("Eloy.");
		developers.add("Jasper.");
		developers.add("Dillon.");
		developers.add("Kian.");
		info.setDevelopers(developers);

		// 맵 형태의 프로퍼티 설정
		Map<String, String> administrators = new HashMap<String, String>();
		administrators.put("Cheney", "cheney@springPjt.org");
		administrators.put("Jasper", "jasper@springPjt.org");
		info.setAdministrators(administrators);

		// 맵 형태의 프로퍼티 설정, String이 아닌 DB 객체를 받음
		Map<String, DataBaseConnectionInfo> dbInfos = new HashMap<String, DataBaseConnectionInfo>();
		dbInfos.put("dev", dataBaseConnectionInfoDev);
		dbInfos.put("real", dataBaseConnectionInfoReal);
		info.setDbInfos(dbInfos);

		return info;
	}
}
