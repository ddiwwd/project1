package project1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;



public class ProApp {

	public static void main(String[] args) {
		project1.ProLogic logic = new project1.ProLogic();
		while(true) {
			//1.메인 메뉴 출력
			logic.printMainMenu();
			//2.메인메뉴 번호 입력받기
			int mainMenu=logic.getMenuNumber();
			//3. 메인메뉴에 따른 분기
			logic.seperateMainMenu(mainMenu);
			
			Map<Character,List<Person>> personBook =new HashMap<>();
			List<Person> NameList=null;
			
		}

	}

}
