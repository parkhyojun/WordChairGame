package word.View;

import java.util.Scanner;

public class WordView {
	private Scanner sc;
	
	public WordView() {
		super();
		sc = new Scanner(System.in);
		
	}


	public int showMenu() {		//메뉴보기
		System.out.println("----- 끝말잇기 -----");
		System.out.println("1. 게임시작");
		System.out.println("2. 전적보기");
		System.out.println("0. 프로그램 종료");
		System.out.print("선택 : ");
		int sel = sc.nextInt();
		return sel;
	}


	public void startGame() {	//게임시작 메소드
		System.out.println("----- 게임시작 -----");
	}


	public void comTurn(String word) {	//컴퓨터 단어 출력 메소드
		System.out.println("컴퓨터 :" + word);
	}


	public String userTurn(char lastLetter) {
		System.out.println("["+lastLetter+"]로 시작하는 단어를 입력하세요 :");
		System.out.println("포기하는 경우 gg입력 ");
		System.out.print("단어 입력 : ");
		String word = sc.next();	//단어이므로 띄어쓰기 제외
		return word;
	}


	public void loseMsg() {
		System.out.println("패배..");
	}


	public void noSearchWord() {
		System.out.println("단어를 찾을 수 없습니다.");
	}


	public void wrongMsg() {
		System.out.println("잘못 입력하셨습니다.");
	}


	public void winMsg() {
		System.out.println("승리 !!");
	}
	
}