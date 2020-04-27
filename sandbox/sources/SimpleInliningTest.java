public class SimpleInliningTest {
	public volatile long sum = 0;
	
	public int add(int a, int b) {
		int temp = a + b;
		sum += temp;
		return temp;
	}
 
	public static void main(String[] args) {
		SimpleInliningTest test = new SimpleInliningTest();
 
		int sum = 0;
 
		for (int i = 0; i < 1000000; i++) {
			sum = test.add(sum, 1);
		}
 
		System.out.println("Sum:" + sum);
		System.out.println("Test.sum:" + test.sum);
	}
}