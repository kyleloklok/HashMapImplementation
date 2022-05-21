public class HashMap<K, V> {
    private Node[] items;
    private static int load_capacity = 16; //DEFAULT: 16
    private int size;
    private int filledSlots;

    public HashMap(){
        this.items = new Node[load_capacity];
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

    public int getAvailableSlots(){
        return load_capacity - filledSlots;
    }

    public int getFilledSlots(){
        return filledSlots;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void put(K key, V val){
        int hash = getHashCode(key);
        int ind = generateIndex(hash);
        ind = Math.abs(ind);
        ensureCapacity();
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
        int ind = generateIndex(hash);
        ind = Math.abs(ind);
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
        int ind = generateIndex(hash);
        ind = Math.abs(ind);
        Node<K, V> current = items[ind];
        if(current.next == null){
            if(current.hash == hash && current.key.equals(key)){
                V item = current.item;
                items[ind] = null;
                size--;
                filledSlots--;
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
            load_capacity *= 2;
            filledSlots = 0;
            Node<K, V>[] temp = new Node[items.length * 2];
            for(int i = 0; i < items.length; i++){
                Node current = items[i];
                while(current != null){
                    int ind = generateIndex(current.hash);
                    ind = Math.abs(ind);
                    if(temp[ind] == null) filledSlots++;
                    temp[ind] = current;
                    current = current.next;
                }
            }
            items = temp;
        }
    }

    private static int generateIndex(int hash){
        return hash % (load_capacity - 1);
    }

    private static int getHashCode(Object o){
        return o != null ? o.hashCode() : 0;
    }

}
