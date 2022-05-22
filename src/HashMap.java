import java.util.Iterator;

public class HashMap<K, V> implements Iterable<K> {
    private Node[] items;
    private int load_capacity = 16;//DEFAULT: 16
    private double load_factor;
    private int size;
    private int filledSlots;

    public HashMap(){
        this.items = new Node[load_capacity];
        load_factor = 0.75;
    }

    public HashMap(int load_capacity, double load_factor){
        this.load_capacity = load_capacity;
        this.items = new Node[this.load_capacity];
        this.load_factor = load_factor;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator<>();
    }

    private class Node<E, T>{
        private final int hash;
        private final E key;
        private T item;
        private Node next;
        public Node(int hash, E key, T item, Node next){
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
        if(filledSlots >= items.length * load_factor){
            load_capacity *= 2;
            filledSlots = 0;
            Node<K, V>[] temp = new Node[load_capacity];
            for(int i = 0; i < items.length; i++){
                Node current = items[i];
                while(current != null){
                    items[i] = current.next;
                    current.next = null;
                    int ind = generateIndex(current.hash);
                    if(temp[ind] == null){
                        filledSlots++;
                        temp[ind] = current;
                    } else{
                        Node<K, V> node = temp[ind];
                        while(node.next != null){
                            node = node.next;
                        }
                        node.next = current;
                    }
                    current = items[i];
                }
            }
            items = temp;
        }
    }

    public boolean containsKey(K key){
        int hash = getHashCode(key);
        int ind = generateIndex(hash);
        ind = Math.abs(ind);
        Node<K, V> current = items[ind];
        while(current != null){
            if(current.hash == hash && current.key == key) return true;
            current = current.next;
        }
        return false;
    }

    public boolean containsValue(V val){
        for(Node node : items){
            while(node != null){
                if(node.item.equals(val)) return true;
                node = node.next;
            }
        }
        return false;
    }

    private int generateIndex(int hash){
        return hash % (load_capacity - 1);
    }

    private int getHashCode(Object o){
        return o != null ? o.hashCode() & 0x7fffffff : 0;
    }

    private class KeyIterator<I> implements Iterator<I>{
        int pos;
        I[] keys;

        public KeyIterator(){
            keys = (I[]) new Object[size];
            int p = 0;
            for(int i = 0; i < items.length; i++){
                if(items[i] == null) continue;
                Node current = items[i];
                while(current != null){
                    I key = (I) current.key;
                    keys[p++] = key;
                    current = current.next;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return pos < keys.length;
        }

        @Override
        public I next() {
            if(!hasNext()) return null;
            return keys[pos++];
        }
    }

}
