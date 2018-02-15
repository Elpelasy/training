package trails;

public class Man extends Human {
	
	private String name;
	private int age;
	private static boolean canFly = false;

	
	public static void main(String[] args) {
		Man m1 = new Man();
		m1.name = "ahmed"; 
		m1.age = 25;
		
		System.out.println(m1.name + "  Can Fly: " + Man.canFly);
		
		Man m2 = new Man();
		m2.name = "mohamed"; 
		m2.age = 26;
		m2.canFly = true;
		
		System.out.println(m1.name + "  Can Fly: " + Man.canFly);
		
	}
}
