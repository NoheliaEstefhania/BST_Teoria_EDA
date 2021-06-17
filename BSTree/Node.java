package BSTree;

class Node <E>{
	private E data;
	private Node <E> left;
	private Node <E> right;

	public Node(E data, Node<E> left, Node<E> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
	}

	public Node (E data){
		this(data,null, null);
	}

	public E getData() {
		return data;
	}

	public void setData(E data) {
		this.data = data;
	}

	public Node<E> getLeft() {
		return left;
	}

	public void setLeft(Node<E> left) {
		this.left = left;
	}

	public Node<E> getRight() {
		return right;
	}

	public void setRight(Node<E> right) {
		this.right = right;
	}

}