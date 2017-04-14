package rbTrees;

public class Node <K extends Comparable<K>, V extends Comparable<V>> {
	private K key;
	private V value;
	
	protected Node<K, V> left = null;
	protected Node<K, V> right = null;
	protected Node<K, V> parent = null;
	
	private char color;
	
	public Node(K key, V value){
		this.key = key;
		this.value = value;
		this.color = 0;
	}
	
	public String toString(){
		return "[" + key + " " + value + " " + color + "]";
	}
	
	public Node(K key, V value, char color){
		this.key = key;
		this.value = value;
		this.color = color;
	}

	public char getColor() {
		return color;
	}

	public void setColor(char color) {
		this.color = color;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}

//	public int compareTo(K key) {
//		return getKey().compareTo(key);
//	}
//	
//	public int compareTo(V value){
//		return getValue(). compareTo(value);
//	}


}
