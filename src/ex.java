import java.util.*;

public class ex {
    static void twoSum(int[] nums, int target) {
        int temp1,temp2,n,len;
        n = 0;
        temp1 = nums[n];
        temp2 = nums[n+1];
        len = nums.length;
        boolean Found = false;
        int[] arr = new int[2];
        while(Found==false)
        {
            for(int i=0;i<len-1;i++)
            {
                if((temp1+temp2)==target)
                {

                    arr[0] = temp1;
                    arr[1] = temp2;

                    System.out.println(temp1+" "+temp2);
                    Found = true;
                }
                else {
                    System.out.println("not found");
                }
                n++;
                if(i<len-1)
                {
                    i=0;
                    len-=1;
                }
            }
        }


    }
    public static void main(String[] args) {
        int[] num = {1,2,3,4};
        int tar = 6;

    }
}
