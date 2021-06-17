package BSTree;

public class Test {
	public static void main(String[] args) {
		BST<Integer> b = new BST <Integer>(); 

		try {
			b.insert(10);
			b.inOrden();
			b.insert(20);
			b.inOrden();
			b.insert(7);
			b.inOrden();
		}
		catch(ItemDuplicated x) {
			System.out.println(x.getMessage());
		}
	}
}
