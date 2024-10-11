package lecture_ssafy.day20241011.kmp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

// KMP 알고리즘(Knuth–Morris–Pratt Algorithm) 
// O(N+M)
//1)실패함수: 패턴에서 접두사와 접미사가 같을때의 길이를 구함
//i=1(접미사), j=0 (접두사)부터 문자 비교 시작
//일치하지 않으면  j(비교할 패턴의 수정위치) = fail[j-1] 조정 후 비교: 문자가 일치하거나 j ==0 일때까지 계속 비교
//일치 => i,j 위치 모두 증가하고 fail[i] = ++j;

//2)텍스트와 비교: 텍스트 길이 만큼 패턴과 비교
//i,j모두 0에서부터 비교
public class String_KMPTest {
	static ArrayList<Integer> list = new ArrayList<Integer>();
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		char[] text = {'a','a','a','a','a','a','b'};
		char[] pattern = {'a','a','a'};
		
		int tLength = text.length, pLength = pattern.length;
		
		//1) 실패함수 만들기 :
		int[] pi = new int[pLength];

	    for(int i = 1, j = 0; i < pLength; i++){

			while(j > 0 && pattern[i] != pattern[j]) {
				j = pi[j - 1];
			}

	       if(pattern[i] == pattern[j]) {
			   j++;
			   pi[i] = j;
		   } else {
			   pi[i] = 0;
		   }
	    }

		//2)텍스트와 비교하기
		for(int i=0,j=0; i< tLength; ++i) {

			//1.불일치: j값 조정.
			while (j > 0 && text[i] != pattern[j]) {
				j = pi[j - 1];
			}

			if (text[i] == pattern[j]) {
				j++;
			}

			if (j == pLength) {
				cnt++;
				list.add(i - pLength + 1);
				j = pi[j - 1];
			}
		}

		System.out.println(cnt);
		System.out.println(list);
	}
}