package BSTree;

public class BST <E extends Comparable <E>>{ //el E solo puede ser algo, donde estos dos elem sean comparables
	//regla de orden --> menores a izquierda, mayores a la derecha
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

		public Node (E data){ //nodo vacio
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
	//Atributos
	private Node<E> root; //raiz

	public BST(){ //arbol vacio
		this.root = null;
	}

	public E search(E x) throws ItemNotFound {  //no encontré mi elem
		Node<E>res = searchNode(x,root); //envío lo que estoy buscando y mi punto de partida
		if(res == null) 
			throw new ItemNotFound ("El dato "+ x + " no esta");
		return res.data;
	}

	protected Node<E> searchNode(E x, Node<E> n){  //enviaré lo que estoy buscando y mi punto de partida
		if (n == null) 
			return null; // si es que n es nulo, el nodo raaiz no exite
		else { //si pasa aquí, quiere decir que si existe
			int resC = n.data.compareTo(x); //comparo lo que está guardado en mi nodo, en relación al dato que estoy buscando
			if (resC < 0) return searchNode(x, n.right); //si resulta negativo, implica que mi nodo raiz es menor al que el buscando, entonces mi busqueda tendrìa que ser por la derecha
			else if (resC > 0) return searchNode(x, n.left); //si resulta positivo mi busqueda serìa por la derecha
			else return n;
		}
	}

	//INSERT
	public void insert(E x) throws ItemDuplicated { //no aceptará duplicados, ingreso el dato que deseo insertar
		this.root = insertNode(x, this.root); //mètodo recursivo de apoyo
	}

	protected Node<E> insertNode(E x, Node<E> actual) throws ItemDuplicated {
		Node<E> res = actual;
		if (actual == null) { 
			res = new Node<E>(x);
		}else {
			//buscamos el lugar para inserción
			int resC = actual.data.compareTo(x);
			if (resC == 0 ) throw new ItemDuplicated(x + "esta duplcado"); //= 0, implica que ese elem, ya está dentro de mi árbol, pero yo no admito duplicados, entonces pasa a la exception
			if (resC < 0) res.right = insertNode(x, actual.right); // si resulta negativo, me desplazo a la derecha y si da nulo creo mi nuevo nodo y retorno la direccion de mi nuevo hijo derecho, y vinculo el dato con mi el nodo padre
			else
				res.left = insertNode(x, actual.left); //si resulta positivo, me voy por la izquierda
		}
		return res; //me devolverà la direcciñon del nodo nuevo, para que pueda ser asignado a su papá
	}

	//REMOVE
	public void remove(E x) throws ItemNotFound { //se quiere eliminar el elem. x
		this.root = removeNode(x, this.root); //recibe el elem que quiere eliminar y el elem donde me ubico, el primer caso siempre será la raiz
	}

	protected Node<E> removeNode(E x, Node<E> actual) throws ItemNotFound { 
		Node<E> res = actual; //res almacena lo que tiene el actual
		if (actual == null) throw new ItemNotFound(x + "no esta"); //si el elem. no se encuentra
		int resC = actual.data.compareTo(x);
		if (resC < 0) res.right = removeNode(x, actual.right); //si el resultado es negativo lo busco por la derecha, luego hago lo mismo empezando por mi hijo derecho
		else if (resC > 0) res.left = removeNode(x, actual.left);
			else if(actual.left != null && actual.right != null){//dos hijos
				res.data = minRecover(actual.right).data; //minRecover, se encargará de buscar mi sucesor, a partir del lado derecho, recupero el dato del nodo que quiero eliminar
				res.right = minRemove(actual.right); //luego elimino ese sucesor que encontré con minRecover
			} else { //1 hijo o ninguno
				res = (actual.left != null) ? actual.left : actual.right; //después de haber encontrado el nodo, me pregunto, si tu hijo izquierdo existe, ese dato es el tendrè que enviarlo al nodo padre, lo guardo en res 
			}
		return res;
	}
	
	/*private Node<E> minRecover() {
		if (actual.left != null)
			return minRecover(actual.left);
		else 
		return actual;
	}*/

	private Node<E> minRecover(Node<E> actual) {
		if (actual.left != null)
			return minRecover(actual.left); //me muevo hasta que ya no tenga izquierda
		else 
		return actual; //nodo menor (sucesor)
	}

	/*public E minRemove() {
		E min = minRecover(); //devuelve el menor del árbol
		this.root = minRemove(this.root);
		return min;
	}*/

	//Elimina el menor de la izquierda de un nodo
	protected Node<E> minRemove(Node<E> actual) {
		if (actual.left != null) { //busca el mínimo
			actual.left = minRemove(actual.left);
		}
		else { //elimina el mínimo
			actual = actual.right;
		}
		return actual;
	}

	public boolean isEmpty(){
		return this.root == null;
	}
	/*public String postOrder(){
		if (this.root != null) 
			return postOrden(this.root);
		else return "*";
	}*/
	private String postOrder(Node<E> actual){
		String res = "";
		if (actual.left != null) res += postOrder(actual.left);
		if (actual.right != null) res += postOrder(actual.right);
		return res + actual.data.toString() + "\n";
	}
	
	public String toString() {
		inOrden(this.root);
		return "";
	}
	
	public void inOrden() {
		if(this.isEmpty())
			System.out.println("El árbol está vacío ...");
		else
			inOrden(this.root);
		System.out.println();
	}

	public void inOrden(Node<E> actual) {
		if (actual.left != null) inOrden(actual.left);
		System.out.print(actual.data.toString()+", ");
		if (actual.right != null) inOrden(actual.right);	
	}
}