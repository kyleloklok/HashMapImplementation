public class HashMap<K, V> {
    private Node[] items;
    private static final int DEFAULT_CAPACITY = 16; //16
    private int size;
    private int filledSlots;

    public HashMap(){
        this.items = new Node[DEFAULT_CAPACITY];
    }

    private class Node<key, val>{
        private final int hash;
        private final key key;
        private val item;
        private Node next;
        public Node(int hash, key key, val item, Node next){
            this.hash = hash;
            this.key = key;
            this.item = item;
            this.next = next;
        }
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void put(K key, V val){
        int hash = getHashCode(key);
        if(hash < 0) hash *= -1;
        int ind = hash % (DEFAULT_CAPACITY - 1);
        ensureCapacity();
        if(ind >= items.length) resize(ind);
        Node<K, V> node = new Node(hash, key, val, null);
        if(items[ind] == null){
            items[ind] = node;
            node.item = val;
            size++;
            filledSlots++;
            return;
        }
        Node current = items[ind];
        while(current != null){
            if(current.key.equals(key)){
                current.item = val;
                node = null;
                return;
            }
            if(current.next == null) break;
            current = current.next;
        }
        current.next = node;
        size++;
    }

    public V get(K key){
        int hash = getHashCode(key);
        if(hash < 0) hash *= -1;
        int ind = hash % (DEFAULT_CAPACITY - 1);
        Node<K, V> current = items[ind];
        while(current != null){
            if(current.hash == hash && current.key == key){
                return current.item;
            }
            current = current.next;
        }
        return null;
    }

    public V remove(K key){
        int hash = getHashCode(key);
        if(hash < 0) hash *= -1;
        int ind = hash % (DEFAULT_CAPACITY - 1);
        Node<K, V> current = items[ind];
        if(current.next == null){
            if(current.hash == hash && current.key.equals(key)){
                V item = current.item;
                items[ind] = null;
                size--;
                return item;
            } else return null;
        }
        while(current != null){
            if(current.next != null && current.next.key.equals(key)) break;
            current = current.next;
        }
        V item = (V) current.next.item;
        current.next = current.next.next;
        size--;
        return item;
    }

    private void ensureCapacity(){
        if(filledSlots >= items.length * 0.75){
            Node<K, V>[] temp = new Node[items.length * 2];
            for(int i = 0; i < items.length; i++){
                temp[i] = items[i];
            }
            items = temp;
        }
    }

    private void resize(int ind){
        Node<K, V>[] temp = new Node[ind + 1];
        for(int i = 0; i < items.length; i++){
            temp[i] = items[i];
        }
        items = temp;
    }

    private static int getHashCode(Object o){
        return o != null ? o.hashCode() : 0;
    }

}
