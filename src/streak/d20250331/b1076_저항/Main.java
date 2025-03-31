package streak.d20250331.b1076_저항;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<String, Long> colorValueMap = new HashMap<>();
    private static final Map<String, Long> colorMultiplierMap = new HashMap<>();

    public static void main(String[] args) throws IOException {

        colorValueMap.put("black", 0L);
        colorValueMap.put("brown", 1L);
        colorValueMap.put("red", 2L);
        colorValueMap.put("orange", 3L);
        colorValueMap.put("yellow", 4L);
        colorValueMap.put("green", 5L);
        colorValueMap.put("blue", 6L);
        colorValueMap.put("violet", 7L);
        colorValueMap.put("grey", 8L);
        colorValueMap.put("white", 9L);

        colorMultiplierMap.put("black", 1L);
        colorMultiplierMap.put("brown", 10L);
        colorMultiplierMap.put("red", 100L);
        colorMultiplierMap.put("orange", 1000L);
        colorMultiplierMap.put("yellow", 10000L);
        colorMultiplierMap.put("green", 100000L);
        colorMultiplierMap.put("blue", 1000000L);
        colorMultiplierMap.put("violet", 10000000L);
        colorMultiplierMap.put("grey", 100000000L);
        colorMultiplierMap.put("white", 1000000000L);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long answer = 0;

        answer += (colorValueMap.get(br.readLine()) * 10);
        answer += (colorValueMap.get(br.readLine()));
        answer *= colorMultiplierMap.get(br.readLine());

        System.out.println(answer);
    }
}
