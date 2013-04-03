
public class mul {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] a = {1,2,3,4,5,6,7,8,9,10,-1,4564,2,-34,345,565756,-999};
		
		System.out.println((new mul()).bla1(a,a.length));
	}
	
	public int bla1(int a[],int n){
		if (n==1)
			return a[0];
		else{
			int tmp = bla1(a,n-1);
			if (tmp <= a[n-1])
				return tmp;
			else
				return a[n-1];						
		}
			
		
	}

}
