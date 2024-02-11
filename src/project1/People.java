package project1;

import project1.Person;
import project1.ProLogic;

public class People extends Person {
	//[멤버변수(필드)]
	public String name;
	public int age;
	public String address;
	public String phoneNum;
	//[인자 생성자]
	public People(String name, int age , String address , String phoneNum) {
		super(name, age,address,phoneNum);
		this.name = name;
		this.age = age;
		this.address = address;
		this.phoneNum = phoneNum;
	}
	//[멤버 메소드]
	@Override
	public String get() {		
		return String.format("이름:%s,나이:%s,주소:%s,연락처:%s",super.get(),name,age,address,phoneNum);
	}
	@Override
	public void print() {		
		System.out.println(get());
	}
	
}
