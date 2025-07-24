package streak.d20250724.p17683_방금그곡;

class Try1 {

	static String convert(String melody) {
		String result = new String(melody);
		result = result.replaceAll("C#", "c");
		result = result.replaceAll("D#", "d");
		result = result.replaceAll("F#", "f");
		result = result.replaceAll("G#", "g");
		result = result.replaceAll("A#", "a");
		return result;
	}

	public String solution(String m, String[] musicinfos) {
		String targetMusic = convert(m);
		System.out.println("찾아야하는 멜로디 : " + targetMusic);

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

			String sourceMusic = convert(musicInfo[3]);
			System.out.println("정보 : " + playTime + " " + sourceMusic);

			int size1 = sourceMusic.length();
			int size2 = targetMusic.length();

			if (size1 < size2) {

				for (int t = 0; t < size1; t++) {
					int cnt = 0;

					for (int k = 0; k < size2; k++) {
						if (sourceMusic.charAt((t + k) % size1) == targetMusic.charAt(k)) {
							cnt++;
						}
					}

					if (cnt == size2) {
						return musicInfo[2];
					}
				}

			} else {

				sourceMusic = sourceMusic + sourceMusic;

				if (sourceMusic.contains(targetMusic)) {
					int startIdx = sourceMusic.indexOf(targetMusic);

					if (startIdx + targetMusic.length() - 1 < playTime) {
						return musicInfo[2];
					}
				}
			}

		}

		return "(None)";
	}
}