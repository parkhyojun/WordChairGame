package word.Controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import word.View.WordView;



public class WordController {
	private ArrayList<String> words;
	private WordView view;
	private Random r;
	private int win;	//이긴 횟수
	private int lose;	//진 횟수
	
	public WordController() {
		super();
		getWordFile();
//		System.out.println(words);
		view = new WordView();
		r = new Random();
	}
	
	public void main() {
		while(true) {
			int sel = view.showMenu();
			switch(sel) {
			case 1:
				startGame();	//1. 게임시작
				break;
			case 2:
				printScore();	//2.스코어 출력
				break;
			case 0:
				return;
			}
		}
	}
	
	

	public void startGame() {
		//1.words리스트 -> 랜덤단어 1개 추출
		int random = r.nextInt(words.size());	//랜덤의 사이즈 정의
		String word = words.get(random);	//랜덤으로 단어 하나 뽑아내기
		view.startGame();					//게임시작 메서드
		view.comTurn(word);					//컴퓨터가 단어 말하기	
		while(true) {
			char lastLetter = word.charAt(word.length()-1);	//마지막 글자를 얻어온다
			String userWord = view.userTurn(lastLetter);
			int userResult = checkWord(userWord,lastLetter);
			if(userResult == -1) {
				return;
			}else if( userResult == 1) {	//잘못입력했을때 -> 계속 진행(while문상단)
				continue; 
			}
			char userLastLetter = userWord.charAt(userWord.length()-1);	
			word = comTurn(userLastLetter);
			if(word==null) {
				win++;
				view.winMsg();
				return;
			}else {
				view.comTurn(word);
			}
		}
	}
	

	public int checkWord(String userWord, char lastLetter) {
		//1. gg입력한경우 -> return -1 -> 게임패배
		if(userWord.equals("gg")) {	
			view.loseMsg();
			lose++;
			return  -1;
		//2. 정상인 경우 	
		//-> 이전단어끝글자와, 입력단어 첫글자가 일치 또는 입력한 단어가 words에 존재하는 경우 -> return 0
		}
		if((userWord.charAt(0))==lastLetter) {
			if(words.contains(userWord)) {
				return 0;
			}else {
				view.noSearchWord();
				return 1;
			}
		}else {	
			view.wrongMsg();
			return 1;
		//3. 비정상인경우
		//3-1. 이전단어끝글자와, 입력단어 첫글자가 다른경우
		//3-2. 이전단어 끝글자와, 입력단어 첫글자는 같은데 입력단어 words에 존재하지 않는 경우 -> return 1
		}
	}
	
	public String comTurn(char lastLetter) {
		//1. 문자열을 저장할 ArrayList생성
		//2. words중에 사용자가 입력한 마지막글로 시작하는 단어를 생성한 리스트에 add
		//만약 마지막글자로 시작하는 단어가 한개도 없으면 -> return null;
		//단어가 있으면 단어중 랜덤단어 1개 리턴
		ArrayList<String> wordList = new ArrayList<String>();
		
		for(String word : words) {
			if(word.charAt(0)==lastLetter) {
				wordList.add(word);
			}
		}
		
		if(wordList.isEmpty()) {
			return null;
		}else {
			int random = r.nextInt(wordList.size());
			return wordList.get(random);
		}
	}
	
	public void printScore() {	//전적 출력
		System.out.println("===== 전적 출력 =====");
		System.out.println("WIN :" + win);		//이긴횟수
		System.out.println("LOSE :" + lose );	//진횟수
	}
	
	
	
	
	public void getWordFile() {		//txt파일 집어넣기
		words = new ArrayList<String>();
		BufferedReader br = null;	//보조 스트림
		try {
			FileReader fr = new FileReader("words.txt");	//주 스트림 생성
			br = new BufferedReader(fr);					//주 스트림에 보조스트림 연결
			while(true) {	
				String word = br.readLine();
				if(word == null) {	//반환할 단어가 없으면
					break;			//break!
			}
				words.add(word);	//words 배열리스트에 word추가
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {	//다쓴 스트림 반환
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}