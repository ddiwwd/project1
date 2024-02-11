package project1;

import java.io.Serializable;

public class Person implements Serializable{

		
		public String name;
		public int age;
		public String address;
		public String phoneNum;
		
		
		
		

		public Person(String name, int age , String address , String phoneNum ) {		
			this.name = name;
			this.age = age;
			this.address = address;
			this.phoneNum = phoneNum;
		}
		@Override
		public String toString() {
			return String.format("[이름:%s,나이:%s,주소:%s,전화번호:%s]", name,age,address,phoneNum);
		}
		public String getName() {
			return name;
		}	
		public int getAge() {
			return age;
		}
		public String address() {
			return address;
		}
		public String getphoneNum() {
			return phoneNum;
		}
			//[멤버 메소드]
			String get() {
				return String.format("이름:%s,나이:%s,주소:%s,연락처:%s",name,age,address,phoneNum);
			}
			void print() {
				System.out.println(get());
			
	}

}
