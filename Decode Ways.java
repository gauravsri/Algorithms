/*
	Decode Ways

	A message containing letters from A-Z is being encoded to numbers using the following mapping:

	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26

	Given an encoded message containing digits, determine the total number of ways to decode it.

	Logic: 1. This problem is similar to climbing stairs just that here we have to check if two digits are less than equal to 26.
		   2. Take two counters count 1 and count 2. count1 is for storing way to decode single digit 
		   	  and count2 to decode digits: digit[i-1] and digit[i]. 
		   3. If previous and current digit form number less than= 26 then current no can be generated by ways till previous number and current count so far.
		   4. If previous and current digit does not form number less= 26 then way to generate current digit is only 1.
		   5. In this process update count2 with count1 while every time moving forward.
		   6. Edge cases: 0 does not result in any letter unless its being used with something else.
*/

public class Solution {
    /**
     * @param s a string,  encoded message
     * @return an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
    	int count2 = 1, count1 = 1;

    	// empty string or leading zero means no way
    	if (s == null || s.length() == 0 || (s.length() == 1 && s.charAt(0) == '0'))
    	    return 0;
    	    
    	for (int i = 1; i < s.length(); i++) {
    		// zero voids ways of the last because zero cannot be used separately
    		if (s.charAt(i) == '0')
    			count1 = 0;

    		// possible two-digit letter, so new r1 is sum of both while new r2 is the old r1
    		if (s.charAt(i-1) == '1' || s.charAt(i-1) == '2' && s.charAt(i) <= '6') {
    			int t = count1;
    			count1 = count1 + count2;
    			count2 = t;
    		} else {
    			// one-digit letter, no new way added
    			count2 = count1;
    		}    		
    	}
    	return count1;
    }
}