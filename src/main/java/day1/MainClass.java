package day1;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// 스프링 컨테이너 사용 전
		// TransportationWalk transportationwork = new TransportationWalk();
		// transportationwork.move();

		// 스프링 컨테이너 사용
		// 생성한 컨테이너에 접근하기 위한 객체 생성
		// GenericApplicationContext 하위의 GenericXmlApplicationContext
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("classpath:applicationContext.xml");

		// 스프링 컨테이너에서 생성한 빈 객체를 가져오고 변수로 선언하기
		TransportationWalk transportationWalk = context.getBean("walk", TransportationWalk.class);

		// 가져온 빈 객체 사용하기
		transportationWalk.move();

		// 사용한 빈 객체 반환
		context.close();
	}
}
