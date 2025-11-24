package daily.y2025.m07.d24.p17683_방금그곡;

class Solution {

	static String convert(String melody) {
		String result = new String(melody);
		result = result.replaceAll("C#", "c");
		result = result.replaceAll("D#", "d");
		result = result.replaceAll("F#", "f");
		result = result.replaceAll("G#", "g");
		result = result.replaceAll("A#", "a");
		result = result.replaceAll("B#", "b");
		return result;
	}

	public String solution(String m, String[] musicinfos) {
		int maxPlayTime = 0;
		String targetMusic = convert(m);
		String answer = "";

		for (int i = 0; i < musicinfos.length; i++) {
			String[] musicInfo = musicinfos[i].split(",");
			String[] startTime = musicInfo[0].split(":");
			String[] endTime = musicInfo[1].split(":");

			int playTime =
				(Integer.parseInt(endTime[0]) * 60 + Integer.parseInt(endTime[1]))
					- (Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]));

			if (targetMusic.length() > playTime) {
				continue;
			}

			String orignalSourceMusic = convert(musicInfo[3]);
			int size = orignalSourceMusic.length();
			StringBuilder sourceMusic = new StringBuilder("");

			for (int j = 0; j < (int) (playTime / size); j++) {
				sourceMusic.append(orignalSourceMusic);
			}

			for (int j = 0; j < (int) (playTime % size); j++) {
				sourceMusic.append(orignalSourceMusic.charAt(j));
			}

			if (sourceMusic.toString().contains(targetMusic) && maxPlayTime < playTime) {
				maxPlayTime = playTime;
				answer = musicInfo[2];
			}
		}

		if (answer == "") {
			return "(None)";
		} else {
			return answer;
		}
	}
}