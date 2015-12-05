package nyc.c4q.personabe1984;

/**
 * 1.Convert 134 and 562 to binary, add them, convert the sum back to decimal.
 *  Confirm your final answer is 696(= 134 + 562)
 *
 * 2.Do the same for 51 and -8. Confirm your final answer is 43(= 51 - 8)
 *
 * 3.What's 52 in octal (base 8)?
 *
 * 4.Read about 'od' or 'hexdump' and how they read/display bytes. Use either
 *  one and plya around with the options to view the human-readable information inside
 *      1)any downloaded Facebook photo.
 *      2)any Java source file
 *      3)any Java class file
 *
 * 5.Using your preferred save this text "" using Cyrillic(ISO 8859-5).
 *  Open the file using Chorome. Play with different encoding and observe the difference.
 *
 * 6.Given an integer, write code to print out its bitstring
 *
 * 7.Given an integer, write code to count the number of 1s its bitstring
 *
 * 8.Given an integer, write code to determine whether its bitstring is a palindrome
 *
 * 9.Implement Lempel-Ziv-Welch compression.
 */



public class BitstringMethods {

    public static final int NUM_JAVA_INT_BITS = 32;

    private static String convertToBitstring(int number){
        char[] bitsAaChars = new char[NUM_JAVA_INT_BITS];
        int writeIndex = NUM_JAVA_INT_BITS-1; //writing from right to left

        while(number != 0){
            int lsb = number & 0x01;

            if(lsb == 1)
                bitsAaChars[writeIndex] = '1';
            else
                bitsAaChars[writeIndex] = '0';

            writeIndex--;

            number >>>= 1;
        }

        String bitString = new String(bitsAaChars,writeIndex+1,NUM_JAVA_INT_BITS - writeIndex -1);

        return bitString;
    }

    private static int countOneBits(int number){
        int count = 0;
        while(number != 0){
            int lsb = number & 0x01;
            if(lsb == 1){
                count++;
            }
            number >>= 1;
        }
        return count;
    }

    private static boolean isBinaryPalindrome1(int number){
        String bitString = convertToBitstring(number);
        return isPalindrome(bitString);
    }

    private static boolean isPalindrome(String s){
        int l = 0;
        int r = s.length()-1;

        while(l<r){
            if(s.charAt(l) != s.charAt(r)){
                return false;
            }

            l++;
            r++;
        }

        return true;
    }

    private static boolean isBinaryPalindrome2(int number){
        if(number == 0)return true;

        int leftMask = 1 << (NUM_JAVA_INT_BITS - 1);

        while((leftMask & number) == 0){
            leftMask >>>= 1;
        }

        int rightMask = 1;

        while(leftMask > rightMask){
            int leftBit = number & leftMask;
            int rightBit = number & rightMask;

            if(leftBit == 0 && rightBit != 0) return false;
            if(leftBit != 0 && rightBit == 0) return false;

            leftMask >>>= 1;
            rightMask <<= 1;

        }

        return true;
    }


}