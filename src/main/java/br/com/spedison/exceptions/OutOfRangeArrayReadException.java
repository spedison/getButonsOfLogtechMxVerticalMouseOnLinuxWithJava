package br.com.spedison.exceptions;

public class OutOfRangeArrayReadException extends RuntimeException{
    public OutOfRangeArrayReadException(String arrayName, int arrayLen, int endIndex) {
        super("Problems while read array " + arrayName+ ". The length of array is " + arrayLen + " and the last index is " +  endIndex );
    }
}
