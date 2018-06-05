import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;


public class ArrayHopper {

	private HashSet<Integer> allPrimes = new HashSet<Integer>();
	private final int UPPER_LIMIT = 100;
	public ArrayHopper() {
		
	}
	
	public void fillPrimesSet() {
		allPrimes.add(new Integer(2));
		allPrimes.add(new Integer(3));
		
		for(int i=5; i<UPPER_LIMIT; i++) {
			int sqrroot = (int) Math.sqrt(i);
			boolean factors_present = false;
			
			for(int j=2; j <= sqrroot; j++ ) {
				if(( i%j ) == 0) {
					factors_present = true;
					break;
				}				
			}
			if(!factors_present) {
				allPrimes.add(new Integer(i));
			}
		}
		//System.out.println("Hashset is.." + allPrimes);
	}
	
	public boolean areAllPrimes(int [] entries, int n) {
		for(int i=0; i<n; i++) {
			if(!allPrimes.contains(entries[i])) {
				return false;
			}
		}
		return true;
	}
	
	public boolean islastPairPrimeConvertible (int first, int second) {
		int a = first;
		int b = second;
		while(a > 1 && b < UPPER_LIMIT ) {
			if(allPrimes.contains(a) && allPrimes.contains(b)) {
				return true;
			}
			a -= 1;
			b += 1;
		}
		
		a = first;
		b = second;
		while(b > 1 && a < UPPER_LIMIT) {
			if(allPrimes.contains(a) && allPrimes.contains(b)) {
				return true;
			}
			a += 1;
			b -= 1;
		}
		return false;
	}
	
	public boolean isSecondValueInPairPrimeConvertible (ArrayList<Integer> currentList, int first, int second, int indexFirst) {
	
		int a = first;
		int b = second;
		while(a > 0 && b <= UPPER_LIMIT ) {
			if(allPrimes.contains(b)) {
				currentList.set(indexFirst, a);
				return true;
			}
			a -= 1;
			b += 1;
		}
		
		a = first;
		b = second;
		while(b > 0 && a <= UPPER_LIMIT) {
			if(allPrimes.contains(b)) {
				currentList.set(indexFirst, a);
				return true;
			}
			a += 1;
			b -= 1;
		}		
		return false;
	}
	
	public boolean isListPrimeConvertible(ArrayList<Integer> currentList, int size) {
		if(size == 0 ) {
			return true;
		}
		if(size == 1) {
			if(allPrimes.contains(currentList.get(0))) {
				return true;
			}
			return false;
		}
		if(size == 2) {
			if(islastPairPrimeConvertible(currentList.get(0), currentList.get(1))) {
				return true;
			}
			return false;
		}
		else if(size > 2) {
			if(isSecondValueInPairPrimeConvertible(currentList, currentList.get(size-2), currentList.get(size-1), size-2)) {
				return isListPrimeConvertible(currentList, size-1);
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	
	//Making all the sets, with elements with K separated and
	//checking whether adjacent elements are prime convertible
	public boolean findWhetherAllPrimes(int [] allentries, int n, int K ) {
		
		if(areAllPrimes(allentries, n)) {				
			return true;
		}
		
		if(K > n-1) {			
			return false;
		}
		else {
			ArrayList<Integer> templist = new ArrayList<Integer>();
			for(int i=0; i<K ; i++) {
				int j = i;
				templist.clear();
				while(j < n) {
					templist.add(allentries[j]);
					j = j+K;
				}
				if(!isListPrimeConvertible(templist, templist.size())) {
					//System.out.println("set not prime convertible is " + templist);
					//System.out.println("set number not prime convertible is " + i);
					return false;
				}				
			}
		}
		
		return true;
	}

	public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        
        int n = scan.nextInt();
        
        int [] entries = new int[n];
        
        for(int i = 0; i < n; i++){
            entries[i] = scan.nextInt();
        }
        scan.close();
        
        ArrayHopper hopper = new ArrayHopper();
        hopper.fillPrimesSet();
        boolean are_all_primes = hopper.findWhetherAllPrimes(entries, n, k);
        if(are_all_primes) {
        	System.out.println(1);
        }
        else {
        	System.out.println(-1);
        }

	}

}
