
public class Count {

	public  int numOnes(int n){
		if(n==1){
			return 1;
		}
		
		if(n%2==0){
			return numOnes(n/2);
		}else{
			return numOnes(n/2) + 1;
		}
			
	}
	
	public void permute(String str){
		permute(str.toCharArray(),0,str.length());
	}
	
	private void permute(char[] str, int low, int high){
		
		if(low==high){
			for(int i = 0; i <str.length;i++){
				System.out.print("" + str[i]);
			}
			System.out.println();
		}
		
		for(int i =low; i < high; i++){
				
				swap(str,low,i);
				permute(str, low + 1,high);					
				swap(str,low,i);
				
		}
		

	}
	
	public void swap(char[] str,int i,int j){
		char tmp = str[i];
		str[i] = str[j];
		str[j] = tmp;
	}
	
	

	
	public static void main(String[] args) {
		Count c = new Count();
		System.out.println(c.numOnes(25));
		c.permute("abc");
	}

}
