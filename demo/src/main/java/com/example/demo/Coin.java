package com.example.demo;

class Coin {
	// m is size of coins array (number of different coins)
	static int minCoins(int V) {
		// base case
		if (V == 0)
			return 0;

		int coins[] = { 9, 6, 5, 3 };

		// Initialize result
		int res = Integer.MAX_VALUE;

		// Try every coin that has smaller value than V
		for (int c : coins) {
		
			if (c <= V) {
				int sub_res = minCoins(V - c);
				
				// Check for INT_MAX to avoid overflow and see if
				// result can minimized
				if (sub_res != Integer.MAX_VALUE && sub_res + 1 < res)
					res = sub_res + 1;
			}
			
		}
		return res;
	}

	public static int count( int n )
	{
	    // table[i] will be storing the number of solutions for
	    // value i. We need n+1 rows as the table is constructed
	    // in bottom up manner using the base case (n = 0)
	    int table[]=new int[n+1];
	 
	    int coins[] = { 9, 6, 5, 3 };
	    int m = coins.length;
	    // Base case (If given value is 0)
	    table[0] = 1;
	 
	    // Pick all coins one by one and update the table[] values
	    // after the index greater than or equal to the value of the
	    // picked coin
	    for(int i=0; i<m; i++)
	        for(int j=coins[i]; j<=n; j++)
	            table[j] += table[j-coins[i]];
	 
	    return table[n];
	}
	
	public static void main(String args[]) {
		int V = 12;
		System.out.println("Minimum coins required is " + count(V));
		
	//	System.out.println("Minimum coins required is " + minCoins(V));
	}
}