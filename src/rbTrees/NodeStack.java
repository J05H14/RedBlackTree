package rbTrees;

import java.util.ArrayList;

public class NodeStack <K extends Comparable<K>, V extends Comparable<V>>{
	private ArrayList<Node<K, V>> list = new ArrayList<Node<K, V>>();
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	public int getSize(){
		return list.size();
	}
	
	public Node<K, V> peek(){
		return list.get(getSize() - 1);
	}
	
	public Node<K, V> pop(){
		Node<K, V> node = list.get(getSize() - 1);
		list.remove(getSize() - 1);
		return node;
	}
	
	public void push(Node<K, V> node){
		list.add(node);
	}
	
	public String toString(){
		return "Stack: " + list.toString();
	}
}
