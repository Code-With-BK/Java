/**
 * 
 * @author Balkrishna Srivastava
 * Copyright © CodeWithBK 2024. All rights reserved.
 * 
 * Java program to check if an integer is a kaprekar number using String
 */

public class KaprekarNumber1 {

    static boolean isKaprekarNumber(int k) {
        String kString = "" + k;
        int n = kString.length();
        int squareOfK = k*k;
        String squareOfKString = "" + squareOfK;
        int l = squareOfKString.length();
        String rightHalfString = squareOfKString.substring(l-n);
        String leftHalfString = squareOfKString.substring(0, l-n);
        int rightHalf = Integer.parseInt(rightHalfString);
        int leftHalf = (leftHalfString.length() == 0)? 0 : Integer.parseInt(leftHalfString);
        if( rightHalf+leftHalf == k ){
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String args[]){
        int k = 297;
        System.out.println("Is " + k + " a Kaprekar Number? " + (isKaprekarNumber(k)?"YES":"No"));
    }
}
