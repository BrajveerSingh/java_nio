package ds;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'isValid' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts STRING s as parameter.
     */

    public static String isValid(String s) {
        if(s == null || s.isEmpty()){
            return "YES";
        }
        Map<Character, Long> frequencyByCharacter =  new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            long count = frequencyByCharacter.getOrDefault(ch, 0L);
            frequencyByCharacter.put(ch, count+1);
        }
        Collection<Long> frequencies = frequencyByCharacter.values();
        int count = 0;
        long previousVal = -1;
        System.out.println(frequencyByCharacter);
        for (long frequency : frequencies){
            System.out.println("previousVal = " + previousVal + " , frequency="+ frequency);
            if(previousVal == -1){
                previousVal = frequency;
            } else if(previousVal - frequency == 0 ){
                previousVal = frequency;
            } else {
                count++;
            }
            if (count == 1){
                previousVal = frequency;
            }
            System.out.println("count=" + count );
//            else if (previousVal == frequency){
//                previousVal = frequency;
//            } else if(previousVal == frequency + 1 || previousVal == frequency -1) {
//                count++;
//                previousVal = frequency;
//            } else {
//                return "NO";
//            }
            if(count > 1) {
                return "NO";
            }
        }

        return count <= 1 ? "YES" : "NO";
            
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
