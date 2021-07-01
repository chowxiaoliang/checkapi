package collections.sort;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

        int[] ints = {1,2,534,23,75,92,9,29};
        sort(ints,0,ints.length-1);
        System.out.println(Arrays.toString(ints));
    }

    private static int divide(int[] a ,int start, int end){
        int base = a[end];
        while (start < end){

            while (start < end && a[start] <= base){
                start ++;
            }

            if (start < end){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                end --;
            }

            while (start < end && a[end] >= base){
                end --;
            }

            if (start < end){
                int temp = a[start];
                a[start] = a[end];
                a[end] = temp;
                start ++;
            }
        }
        return end;
    }

    private static void sort(int[] a, int start, int end){
        if (a.length == 0 || start > end){
            return;
        }
        int position = divide(a, start, end);
        sort(a, start, position-1);
        sort(a, position+1, end);

    }

}
