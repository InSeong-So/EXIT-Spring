# 자바 스프링 프레임워크

## DI(Dependency Injection)

### 기존의 객체 개념
> 배터리 일체형 : 배터리가 떨어지면 장난감을 새로 구입해야 한다.

> 배터리 분리형 : 배터리가 떨어지면 배터리만 교체하면 된다.

> 객체가 일체형이라면 수정시 문제가 발생했을 때 전체를 교체해야하지만 분리형이라면 필요한 부분만 수정이 가능하므로 재사용성, 이식성, 유연성이 증가된다.

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
스프링 컨테이너에서 @Autowired에 접근하는 순간 애노테이션을 적용한 객체의 데이터 타입을 가진 빈 객체를 찾아 알맞은 데이터를 주입하는 방식이다.
```

#### @Resources
```sh
주입하려고 하는 객체의 이름이 일치하는 객체를 자동으로 주입한다.
프로퍼티, 메소드에만 사용하고 생성자에는 사용할 수 없다.
@Autowired와 거의 동일하나 '일치하는 타입'이 아닌 '일치하는 이름'을 찾는다는 것을 확실히 구분해야한다.
```