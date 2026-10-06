import java.util.*;
class Analyser{
    public static void main(String[] args){
        int[] monthlyUsage = {
            120, 150, 90, 200, 180, 220,
            160, 140, 190, 210, 170, 230
        };
        long totalUsage=0;
        int max=Integer.MIN_VALUE;
        int min=Integer.MAX_VALUE;
        for(int num:monthlyUsage){
            totalUsage+=num;
            max=max>num?max:num;
            min=min<num?min:num;
        }
        System.out.println("Total Usage: "+totalUsage);
        double avg=(double)totalUsage/(monthlyUsage.length);
        System.out.printf("Avg Usage: %.2f%n",avg);
        System.out.println("Maximum Usage "+max);
        System.out.println("Minimum Usage "+min);
        char grade=avg>=Constant.GRADE_A_LIMIT?'A':avg>=Constant.GRADE_B_LIMIT?'B':avg>=Constant.GRADE_C_LIMIT?'C':'D';
        System.out.println("Grade: "+grade);
        int[][] houseUsage = {
                {100, 120, 140},
                {200, 220, 240},
                {300, 320, 340}
        };
        for(int i=0;i<houseUsage.length;i++){
            long totalHouseUsage=0L;
            System.out.print("House "+(i+1)+" : ");
            for(int j=0;j<houseUsage[i].length;j++){
                System.out.print(houseUsage[i][j]+" ");
                totalHouseUsage+=houseUsage[i][j];
            }
            System.out.println();
            System.out.println("Total Usage: "+totalHouseUsage);
            System.out.printf("Average: %.2f%n",(double)totalHouseUsage/houseUsage[i].length);
        }
    }
}