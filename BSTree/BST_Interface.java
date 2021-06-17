package BSTree;

public interface BST_Interface<E>{

	E search(E x);
	void insert(E x);
	void remove(E x);
	E minRemove();
	boolean isEmpty();

}
