package trie;

import java.util.HashMap;
import java.util.Stack;

public class code200730_trie {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TrieTree t = new TrieTree();
		System.out.println("insert 'geek'");
		t.insert("geek");
		System.out.println("insert 'geeks'");
		t.insert("geeks");
		System.out.println("insert 'geer'");
		t.insert("geer");
		System.out.println("insert 'gee'");
		t.insert("gee");
		t.printRoot();
		System.out.print("find 'geeks' ->");
		System.out.println(t.find("geeks"));
		System.out.print("find 'geep' ->");
		System.out.println(t.find("geep"));
		
		System.out.println("delete 'gel'");
		t.delete("gel");
		t.printRoot();
		System.out.println("delete 'geek'");
		t.delete("geek");
		t.printRoot();

	}

}

class TrieNode {
	private HashMap<Character, TrieNode> children;
	private boolean endOfWord;

	@Override
	public String toString() {
		return "TrieNode [children=" + children + ", endOfWord=" + endOfWord + "]";
	}

	public TrieNode() {
		// TODO Auto-generated constructor stub
		children = new HashMap<>();
	}

	public HashMap<Character, TrieNode> getChildren() {
		return children;
	}

	public void setChildren(HashMap<Character, TrieNode> children) {
		this.children = children;
	}

	public boolean isEndOfWord() {
		return endOfWord;
	}

	public void setEndOfWord(boolean endOfWord) {
		this.endOfWord = endOfWord;
	}

}

class TrieTree {
	private TrieNode root;

	public TrieTree() {
		root = new TrieNode();
	}

	public void printRoot() {
		System.out.println();
		System.out.println("---------- tree");
		Stack<Character> stack = new Stack<>();
		print(root, stack);
		System.out.println("---------------");
		System.out.println();
	}

	private void print(TrieNode current, Stack<Character> stack) {
		for (char ch : current.getChildren().keySet()) {
			stack.add(ch);
			if (current.getChildren().get(ch).isEndOfWord()) {
				for (char c : stack) {
					System.out.print(c);
				}
				System.out.println();
			}
			print(current.getChildren().get(ch), stack);
			stack.pop();
		}
	}

	public void insert(String word) {
		TrieNode current = root;
		for (char l : word.toCharArray()) {
			current = current.getChildren().computeIfAbsent(l, c -> new TrieNode());
		}
		current.setEndOfWord(true);
	}

	public boolean find(String word) {
		TrieNode current = root;
		for (int i = 0; i < word.length(); i++) {
			char ch = word.charAt(i);
			TrieNode node = current.getChildren().get(ch);
			if (node == null) {
				return false;
			}
			current = node;
		}
		return current.isEndOfWord();
	}

	public void delete(String word) {
		delete(root, word, 0);
	}

	private boolean delete(TrieNode current, String word, int idx) {
		if (idx == word.length()) {
			if (!current.isEndOfWord()) {
				return false;
			}
			current.setEndOfWord(false);
			return current.getChildren().isEmpty();
		}
		char ch = word.charAt(idx);
		TrieNode node = current.getChildren().get(ch);
		if (node == null) {
			return false;
		}
		boolean shouldDeleteCurrentNode = delete(node, word, idx + 1) && !node.isEndOfWord();

		if (shouldDeleteCurrentNode) {
			current.getChildren().remove(ch);
			return current.getChildren().isEmpty();
		}
		return false;
	}
}