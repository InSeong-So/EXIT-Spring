# 자바 스프링 프레임워크

## DI(Dependency Injection)

### 기존의 객체 개념
> 배터리 일체형 : 배터리가 떨어지면 장난감을 새로 구입해야 한다.

> 배터리 분리형 : 배터리가 떨어지면 배터리만 교체하면 된다.

> 객체가 일체형이라면 수정시 문제가 발생했을 때 전체를 교체해야하지만 분리형이라면 필요한 부분만 수정이 가능하므로 재사용성, 이식성, 유연성이 증가된다.

## DI(의존성 주입) 개념
* day1.toy package 참조
> 배터리를 필요로하는 장난감 = 배터리에 의존적 = 배터리를 분리하여 개발 후 의존성 주입

### 일체형 : 생성자에서 객체 주입
```sh
public class ElectronicCarToy{
    private Battery battery;

    public ElectronicCarToy(){
        battery = new NormalBatter();
    }
}
```

### 분리형 1 : setter를 이용한 객체 주입
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

### 분리형 2 : 생성자, setter를 이용한 객체 주입
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

## 스프링의 의존 객체 주입방법
### 생성자를 이용한 의존 객체 주입
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

### setter를 이용한 의존 객체 주입
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

### List 타입 의존 객체 주입
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

### Map 타입 의존 객체 주입
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
