package tools;

public class Experiment1 {
	
	
	public static void main (String[]args) {
		fibo(2);
	}
	
	
	/*
	 * 1 comes out
looks back
adds to itself

1 comes out
looks back
adds to itself

2 comes out
looks back
adds to itself*/
	
	public static void fibo(int counter) {
		
		System.out.println("lmao");
		
		
		if (counter==0) return;
		fibo(counter--);
	}
	
}
