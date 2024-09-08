package lab9;

import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        }else if (key.compareTo(p.key) > 0) {
            return getHelper(key, p.right);
        }else if (key.compareTo(p.key) == 0) {
            return p.value;
        }else {
            return null;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if(p == null) {
            size++;
            return new Node(key, value);
        }
        else if (key.compareTo(p.key) < 0) {
            return putHelper(key, value, p.left);
        }
        else if (key.compareTo(p.key) > 0) {
            return putHelper(key, value, p.right);
        }
        else {
            p.value = value;
            return p;
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    private V removeHelper(Node p) {
        // 找到右子树中的最小节点（后继节点）
        Node tmp = p.right;
        Node parent = p; // 记录后继节点的父节点
        while (tmp.left != null) {
            parent = tmp;
            tmp = tmp.left;
        }

        // 将要删除的节点的值替换为后继节点的值
        V oldValue = p.value;
        p.value = tmp.value;

        // 删除后继节点
        if (parent.left == tmp) {
            parent.left = tmp.right;  // 如果后继节点有右子节点，将其连接到父节点的左子树
        } else {
            parent.right = tmp.right; // 如果后继节点是父节点的右子节点，直接删除它
        }

        return oldValue;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        Node p = root;
        Node parent = null;

        // 查找要删除的节点及其父节点
        while (p != null) {
            if (key.compareTo(p.key) == 0) {
                break;
            }
            parent = p;
            if (key.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }

        // 如果没有找到该节点，返回 null
        if (p == null) {
            return null;
        }

        // 处理有两个子节点的情况
        if (p.left != null && p.right != null) {
            return removeHelper(p);
        }

        // 处理有一个子节点或没有子节点的情况
        Node child = (p.left != null) ? p.left : p.right;

        // 如果要删除的是根节点
        if (parent == null) {
            root = child;  // 如果根节点没有子节点，则 root 设为 null
        } else {
            // 如果要删除的是左子节点
            if (parent.left == p) {
                parent.left = child;
            }
            // 如果要删除的是右子节点
            else {
                parent.right = child;
            }
        }

        return p.value;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
