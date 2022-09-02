import java.util.Scanner;

public class Main {

	public static int getReward(int[] dice) {
		
		int maxIdx = 0;
		for(int i = 0; i < dice.length; i++) {
			if(dice[i]==4) {
				return 50000 + (i+1)*5000;
			}
			if(dice[i] == 3) {
				return 10000 + (i+1)*1000;
			}
			if(dice[i] == 2) {
				for(int j = i+1; j < dice.length; j++) {
					if(dice[j] == 2) {
						return 2000 + 500*(i+1) + 500*(j+1);
					}
				}
				return 1000+(i+1)*100;
			}

			if(dice[i] > 0) {
				maxIdx = i;
			}
		}
		
		return (maxIdx+1)*100;
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < n; i++) {
			int[] dice = new int[6];
			for (int j = 0; j < 4; j++) {
				dice[sc.nextInt() - 1]++;
			}

			max = Math.max(getReward(dice), max);
		}
		
		System.out.println(max);
	}
}
