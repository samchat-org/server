import java.util.TreeSet;

public class InvoiceRadom {
	public static void main(String[] args) {
		int total = Integer.parseInt(args[0]);
		TreeSet<Integer> ts = new TreeSet<Integer>();
		while (true) {
			int random = (int) (Math.random() * 4950);
			random = random - random%50;
			if(random == 0){
				continue;
			}
			if (total - random < 0) {
 				ts.add(total);
				break;
			}
			ts.add(random);
 			total = total - random;
		}
		System.out.println(ts);
	}
}
