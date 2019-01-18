# 자바 스프링 프레임워크

## DI(Dependency Injection)

<hr>

### 기존의 객체 개념
> 배터리 일체형 : 배터리가 떨어지면 장난감을 새로 구입해야 한다.

> 배터리 분리형 : 배터리가 떨어지면 배터리만 교체하면 된다.

> 객체가 일체형이라면 수정시 문제가 발생했을 때 전체를 교체해야하지만 분리형이라면 필요한 부분만 수정이 가능하므로 재사용성, 이식성, 유연성이 증가된다.

<hr>

### DI(의존성 주입) 개념
```sh
구성요소간의 의존 관계가 소스코드 내부가 아닌 외부의 설정파일 등을 통해 정의하는 것이다.
```
* day1.toy package 참조
> 배터리를 필요로하는 장난감 = 배터리에 의존적 = 배터리를 분리하여 개발 후 의존성 주입

#### 일체형 : 생성자에서 객체 주입
```sh
public class ElectronicCarToy{
    private Battery battery;

    public ElectronicCarToy(){
        battery = new NormalBatter();
    }
}
```

#### 분리형 1 : setter를 이용한 객체 주입
```sh
public class ElectronicRobotToy{
    private Battery battery;

    public ElectronicRobotToy(){

    }

    public void setBattery(Battery battery){
        this.battery = battery;
    }
}
```

#### 분리형 2 : 생성자, setter를 이용한 객체 주입
```sh
public class ElectronicRadioToy{
    private Battery battery;

    public ElectronicRadioToy(Battery battery){
        this.battery = battery;
    }

    public void setBattery(Battery battery){
        this.battery = battery;
    }
}
```

> 따라서 가장 유연한 코드는 생성자, setter를 이용해 객체를 분리하는 "분리형 2"이다.

<hr>

### 스프링의 의존 객체 주입방법

#### 생성자를 이용한 의존 객체 주입
* applicationContext2 참조
```sh
public StudentRegisterService(StudentDao studentDao){
    this.studentDao = studentDao;
}
                                        ↓
<bean id="studentDao" class="day1.ems.member.dao.StudentDao" ></bean>
<bean id="registerService" class="day1.ems.member.service.StudentRegisterService">
    // 생성자 파라미터는 studentDao를 참조하라
    <constructor-arg ref="studentDao" ></constructor-arg>
</bean>
                                        ↓
private StudentDao studentDao;
private StudentRegisterService registerService = new StudentRegisterService(studentDao);
```

#### setter를 이용한 의존 객체 주입
```sh
public void setJdbcUrl(String jdbcUrl){
    this.jdbcUrl = jdbcUrl;
}
                                        ↓
<bean id="dataBaseConnectionInfoDev" class="day1.ems.member.DataBaseConnectionInfo">
    // set을 없애고 그 뒤에오는 대문자를 소문자로 변경하면 프로퍼티의 이름이 됨
    <property name="jdbcUrl" value="jdbc:oracle:thin:@localhost:1521:xe" />
    <property name="userId" value="scott" />
    <property name="userPw" value="tiger" />
</bean>
```

#### List 타입 의존 객체 주입
```sh
public void setDevelopers(List<String> developers){
    this.developers = developers;
}
                                        ↓
<property name="developers">
    <list>
        <value>Cheney.</value>
        <value>Eloy.</value>
        <value>Jasper.</value>
        <value>Dillon.</value>
        <value>Kian.</value>
    </list>
</property>
```

#### Map 타입 의존 객체 주입
```sh
public void setAdministrators(Map<String, String> administrators){
    this.administrators = administrators;
}
                                        ↓
<property name="administrators">
    <map>
        <entry>
            <key>
                <value>Cheney</value>
            </key>
            <value>cheney@springPjt.org</value>
        </entry>
        <entry>
            <key>
                <value>Jasper</value>
            </key>
            <value>jasper@springPjt.org</value>
        </entry>
    </map>
</property>
```

<hr>

### 의존 객체 자동 주입
> 개념
```sh
스프링 설정 파일에서 의존 객체를 주입할 때 <constructor-org> 또는 <property> 태그로 의존 대상 객체를
 시하지 않아도 스프링 컨테이너가 자동으로 필요한 의존 대상 객체를 찾아서 의존 대상 객체가 필요한 객체에 주입해 주는 기능이다.
```

> 기존 스프링 컨테이너 설정(의존 객체를 수동으로 주입)
```sh
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
 		http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="wordDao" class="day2.com.word.dao.WordDao" />
	<bean id="registerService" class="day2.com.word.service.WordRegisterService">
		<constructor-arg ref="wordDao" />
	</bean>
	<bean id="searchService" class="day2.com.word.service.WordSearchService">
		<constructor-arg ref="wordDao" />
	</bean>
</beans>
```

> 의존 객체 자동 주입 스프링 컨테이너 설정
```sh
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	<!-- 추가  -->
	xmlns:context="http://www.springframework.org/schema/context"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans 
 		http://www.springframework.org/schema/beans/spring-beans.xsd
 		<!-- 추가  --> 
 		http://www.springframework.org/schema/context
 		<!-- 추가  -->
 		http://www.springframework.org/schema/context/spring-context.xsd">
	<!-- 추가  -->
	<context:annotation-config />
	<bean id="wordDao" class="day2.com.word.dao.WordDao" >
		<!-- <qualifier value="usedDao"/> -->
	</bean>
	<bean id="wordDao2" class="day2.com.word.dao.WordDao" />
	<bean id="wordDao3" class="day2.com.word.dao.WordDao" />
	<bean id="registerService" class="day2.com.word.service.WordRegisterServiceUseAutowired" />
	<bean id="searchService" class="day2.com.word.service.WordSearchServiceUseAutowired" />
</beans>
```

#### @Autowired
```sh
주입하려고 하는 객체의 타입이 일치하는 객체를 자동으로 주입한다.
프로퍼티, 생성자, 메소드에 전부 사용할 수 있다.
스프링 컨테이너에서 @Autowired에 접근하면 애노테이션을 적용한 객체의 데이터 타입을 가진 빈 객체를 찾아 알맞은 데이터를 주입한다.
```

#### @Resources
```sh
주입하려고 하는 객체의 이름이 일치하는 객체를 자동으로 주입한다.
프로퍼티, 메소드에만 사용하고 생성자에는 사용할 수 없다.
@Autowired와 거의 동일하나 '일치하는 타입'이 아닌 '일치하는 이름'을 찾는다는 것을 확실히 구분해야한다.
```

<hr>

### 의존 객체 선택
> 동일한 타입의 빈 객체가 여러개 있을 때 @Autowired는 어떤걸 주입해야하는지 헷갈릴 수 있다.
* 동일한 객체가 2개 이상인 경우 스프링 컨테이너는 자동 주입 대상 객체를 판단하지 못하고 Exception을 발생시킨다.
>> 따라서 개발자가 의존 객체를 선택할 수 있는 방법을 제시할 수 있어야 한다.

> @Autowired : 같은 WordDao 객체지만 @Qualifier(우선통과자)를 사용해 의존 객체 선택
```sh
# xml 설정파일
	<bean id="wordDao" class="day2.com.word.dao.WordDao" >
		<qualifier value="usedDao"/>
	</bean>
	<bean id="wordDao2" class="day2.com.word.dao.WordDao" />
	<bean id="wordDao3" class="day2.com.word.dao.WordDao" />

# class 파일
	@Autowired
	@Qualifier("usedDao")
	private WordDao wordDao;
```

> @Inject : 
```sh
# xml 설정파일
	<bean id="wordDao1" class="day2.com.word.dao.WordDao" />
	<bean id="wordDao2" class="day2.com.word.dao.WordDao" />
	<bean id="wordDao3" class="day2.com.word.dao.WordDao" />

# class 파일
	@Inject
	@Named(value = "wordDao1")
	private WordDao wordDao;
```

#### 의존 객체 자동 주입 체크
> 의존 객체가 스프링 컨테이너에서 생성되지 않은 경우, @Autowired 어노테이션을 붙이면 Exception이 발생한다.

> 이 때 @Autowired(requier=false)를 '적용하면 의존 객체 자동 주입을 하지 않겠다'는 기능이 적용된다.
>> 다만 어떤 개발자도 빈 객체를 생성하지 않고 객체 주입을 하지 않으므로 이런 것도 있다는 것만 알아둘 것.

#### @Inject
```sh
@Autowired와 거의 비슷하게 @Inject 어노테이션을 이용해 의존 객체를 자동으로 주입할 수 있다.
차이점이라면 @Autowired는 required 속성으로 의존 대상 객체가 없어도 Exception을 피할 수 있지만,
@Inject의 경우 required 속성을 지원하지 않는 다는 것이다.
```

<hr>

### 생명주기(Life Cycle)

#### 스프링 컨테이너 생명주기
> GenericXmlApplicationContext를 이용한 스프링 컨테이너 초기화(생성)
>> getBean()을 이용한 빈(Bean)객체 이용
>>> close()를 이용한 스프링 컨테이너 종료


####빈 (Bean) 객체 생명주기
> 스프링 컨테이너 초기화 : 빈(Bean) 객체 생성 및 주입
>> 스프링 컨테이너 종료 : 빈(Bean) 객체 소멸

```sh
빈(Bean) 객체의 생명주기는 스프링 컨테이너의 생명주기와 같다.
빈 객체 생성시점에 호출 : afterPropertiesSet(InitializingBean 인터페이스에서 구현)
빈 객체 소멸시점에 호출 : destory(DiposableBean 인터페이스에서 구현)
```

#### init-method, destroy-method 속성
> 빈 객체 선언시 init-method, destory-method 속성을 적용해 생성 / 소멸 단계 제어
>> 지정한 이름과 동일한 메소드를 의존객체 주입한 대상 내부에 메소드로 구현
>>> 생성 / 소멸 시 자동으로 호출

```sh
# xml 설정파일
	<bean id="bookRegisterService" class="day3.com.brms.book.service.BookRegisterService" 
	init-method="initMethod" destroy-method="destroyMethod"/>
	
# class 파일
	public void initMethod() {
		System.out.println("BookRegisterService 빈(Bean)객체 생성 단계");
	}
	public void destroyMethod() {
		System.out.println("BookRegisterService 빈(Bean)객체 소멸 단계");
	}
```

<hr>

### 어노테이션을 이용한 스프링 설정
> 기존에 스프링 설정을 *.xml 파일로 설정했다면 이제 *.java 파일로 설정한다.
>> 즉, applicationContext.xml 을 어노테이션을 활용한 config.java 파일로 대체한다는 것이다.
```sh
	@Configuration 어노테이션으로 설정 파일임을 알림
	@Bean 어노테이션으로 빈 객체를 생성한다고 알림
```

#### Java 파일 분리
> *.xml 파일을 분리하여 유지하는 것처럼, *.java 파일도 동일하게 분리하여 관리할 수 있다.
>> 이유는 설정 파일의 내부 코드가 길어지면 직관성이 떨어지고 유지보수에 어려움이 생길 수 있기 때문이다.
```sh
마음대로 분리해도 상관 없으나, 분류를 나누어 분리하는 것이 좋다.
```

#### @Import 어노테이션
```sh
# 변경 전
	AnnotationConfigApplicationContext ctx
		= new AnnotationConfigApplicationContext(MemberConfig1.class,
					MemberConfig2.class, MemberConfig3.class);
					
# 변경 후
	@Configuration
	@Import({ MemberConfig2.class, MemberConfig3.class})
	public class MemberConfig1 {
		...	...	...
	}
```
