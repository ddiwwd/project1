package project1;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import common.utils.CommonUtils;
import project1.Person;






public class ProLogic {
	//맴버상수
	public static final int MAX_PERSONS=50;
	//맴버변수
	List<Person> person;
	//생성자
	public  ProLogic () {
		person = new Vector<>();;
		loadPerson();
	}

	public void printMainMenu() {
		System.out.println("====================메인 메뉴====================");
		System.out.println(" 1.입력 2.출력 3.수정 4.삭제 5.검색 9.종료");
		System.out.println("===============================================");
		System.out.println("메인 메뉴번호를 입력하세요?");
	}
		public int getMenuNumber() {
			Scanner sc = new Scanner(System.in);
			
			//방법1]isNumber()메소드 정의해서 처리
			String menuStr;
			while(true) {
				menuStr= sc.nextLine().trim();
				if(!CommonUtils.isNumber(menuStr)) {
					System.out.println("메뉴번호는 숫자만 입력하세요");
					continue;
				}
				break;
			}
			return Integer.parseInt(menuStr);
			//방법2]isNumber()메소드 정의하지 않고 try~catch블락으로 처리
		}////////////////getMenuNumber()
		public void seperateMainMenu(int mainMenu) {
			switch(mainMenu) {
				case 1://입력
					setPerson(); 
					break;
				case 2://출력
					printPerson();
					break;
				case 3://수정
					updatePerson();
					break;
				case 4://삭제
					deletePerson();
					break;
				case 5://검색
					findPersonByName();
					break;
				case 9://종료
					System.out.println("프로그램을 종료합니다");
					System.exit(0);
					break;
				default:
					System.out.println("메뉴에 없는 번호입니다");
			
			}//switch
		

				
		
			
			
		}	
	


		///////////////////seperateMainMenu(int mainMenu)public void seperateMainMenu(int mainMenu) 
		
		


		//            [1] 메인메뉴 1번
		private void setPerson() {

			if(person.size()== MAX_PERSONS) {

				System.out.println("정원이 찼어요...더 이상 입력할 수 없어요");
				return;
			}
			Scanner sc = new Scanner(System.in);
			System.out.println("이름을 입력하세요?");
			String name= sc.nextLine().trim();
			char consonant = CommonUtils.getInitialConsonant(name);
			if(consonant=='0') {
				System.out.println("한글명이 아닙니다");	
			}
			System.out.println("나이를 입력하세요?");
			int age = getAges(sc);
			System.out.println("주소를 입력하세요?");
			String address= sc.nextLine();
			System.out.println("연락처를 입력하세요(숫자만)?");
			String phoneNum =getPNum(sc);
			
			
			person.add(new Person(name, age, address, phoneNum));
			
			savePerson();
			
			
		}


		//      [2] 메인메뉴 2번
		private void printPerson() {
			
			
			
		    if (person.isEmpty()) {
		        System.out.println("명단이 비어 있습니다.");
		        return;
		    }

		    // Create an array of Lists to group persons by the first letter of their name
		    List<Person>[] groups = new ArrayList[55204 - 44032 + 1];
		    for (int i = 0; i < groups.length; i++) {
		        groups[i] = new ArrayList<>();
		    }

		    // Group persons by the first letter of their name
		    for (Person p : person) {
		        char firstLetter = p.getName().charAt(0);
		        if (firstLetter < '가' || firstLetter > '힣') {
		            continue; // Skip if the first letter of the name is not a Korean character
		        }
		        int index = firstLetter - '가';
		        groups[index].add(p);
		    }

		    // Print each group
		    for (int i = 0; i < groups.length; i++) {
		        char groupName = (char) ('가' + i);
		        List<Person> group = groups[i];
		        if (!group.isEmpty()) {
		            System.out.println("[" + groupName + " 으로 시작하는 명단]");
		            for (Person p : group) {
		                System.out.println(p);
		            }
		            System.out.println();
		        }
		    }
		}
			
/*
		private void printPerson() {
		  
		}*/
	/* private void printPerson() {

			/*System.out.println("[명단]");
		    for (Person p : person) {
		        System.out.println(p.get());
		    }*/

		//////printPerson() */
	
		
		//      [3] 메인메뉴 3번
		private void updatePerson() {
			Person findPerson=searchPerson("수정");
			if(findPerson !=null) {
				Scanner sc = new Scanner(System.in);
				//수정
				
				System.out.printf("(현재 %s세)수정할 나이를 입력하세요%n",findPerson.age);
				findPerson.age = Integer.parseInt(sc.nextLine().trim());
				
				System.out.printf("(현재 주소:%s)수정할 주소 입력하세요%n",findPerson.address);
				findPerson.address = sc.nextLine().trim();
				
				System.out.printf("(현재 연락처:%s)수정할 연락처를 입력하세요%n",findPerson.phoneNum);
				findPerson.phoneNum = sc.nextLine().trim();
			}
			if(findPerson ==null) {
				return;
			}
			System.out.printf("[%s가(이) 아래와 같이 수정되었습니다]%n",findPerson.name);
			findPerson.print();//수정 내용을 확인하기 위한 출력
			
		}
		//		[4] 메인메뉴 4번
		private void deletePerson() {
			Person findPerson=searchPerson("삭제");
			if(findPerson !=null) {
				for(Person p:person)
					if(findPerson.equals(p)) {
						person.remove(p);
						System.out.println(String.format("[%s가 삭제되었습니다]", findPerson.name));
						break;
					}
			}
			
		}/////////////deletePerson()
		//		[5] 메인메뉴 5번
		private Person searchPerson(String title) {
			System.out.println(title+"할 사람의 이름을 입력하세요?");
			Scanner sc = new Scanner(System.in);
			String name = sc.nextLine().trim();
			for(Person p:person)
				if(p.name!=null)
					if(p.name.equals(name))
						return p;
			System.out.println(name+"로(으로) 검색된 정보가 없어요");
			return null;
		}//////////searchPerson()
		
		private void findPersonByName() {
			Person findPerson=searchPerson("검색");
			if(findPerson !=null) {
				System.out.println(String.format("[%s로(으로) 검색한 결과]", findPerson.name));
				findPerson.print();
			}
		}//////////////findPersonByName()

		private String getPNum(Scanner sc) {
			String phoneNumber;
			while(true) {
				try {
					phoneNumber=sc.nextLine();
					//전화번호에 "-" 추가
					if (phoneNumber.matches("\\d{11}")) { // 숫자 11자리인 경우 
		                phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 7) + "-" + phoneNumber.substring(7);
		                break;
		            } 
					if (phoneNumber.matches("\\d{10}")) { // 숫자 10자리인 경우 
		                phoneNumber = phoneNumber.substring(0, 3) + "-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6);
		                break;
		            } 
					
					else {
		                throw new NumberFormatException();
		            }
				}
				catch(NumberFormatException e) {
					System.out.println("잘못된 연락처입니다. 다시 입력해주세요(10자리 혹은 11자리)");
				}
			}
			return phoneNumber;
		}
		

		
		
		
		
		private int getAges(Scanner sc) {
			int years;
			while(true) {
				try {
					years=Integer.parseInt(sc.nextLine().trim());
					break;
				}
				catch(NumberFormatException e) {
					System.out.println("나이는 숫자만...");
				}
			}
			return years;
		}
		
			
		
		
		
		
			//입력시  저장용 메소드
			private void savePerson() {
				
				if(person.isEmpty()) {
					System.out.println("파일로 저장할 명단이 없어요");
					return;
				}
				
				ObjectOutputStream out = null;
				try {
				out = new ObjectOutputStream(
						new FileOutputStream("src/project1/NameList.data"));
				
				out.writeObject(person);
				System.out.println("파일이 저장되었습니다");
				}
				catch(IOException e) {
					System.out.println("파일 저장시 오류 입니다:"+e.getMessage());
				}
				finally {
					try {
					if(out != null) out.close();
				}
					catch(Exception e) {}
					}
				}//savePerson
			
			
			
			
			
			
			

				private void loadPerson() {
					
					
		
					ObjectInputStream ois = null;
					try {
					 ois = 
							 new ObjectInputStream(
									 new FileInputStream("src/project1/NameList.data"));
					person=(List<Person>)ois.readObject();
				}
					catch(Exception e) {}
					finally {
						try {
						if(ois != null) ois.close();
					}
						catch(Exception e) {} 
				}///loadPerson 
				 

		}////////


}
