/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        //DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null || value == null) {
            throw new java.lang.IllegalArgumentException("Key or value is null");
        }
        V res = null;
        double newLoad = (double) (size + 1) / table.length;
        if (newLoad > MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        int hashCode = key.hashCode();
        int compressValue = Math.abs(hashCode % table.length);
        if (table[compressValue] == null) {
            table[compressValue] = new ExternalChainingMapEntry<>(key, value);
            size += 1;
        } else {
            if (checkDuplicate(table[compressValue], key)) {
                res = replace(table[compressValue], key, value);
            } else {
                table[compressValue] = new ExternalChainingMapEntry<>(key, value, table[compressValue]);
                size += 1;
            }
        }
        return res;
    }


    // check whether if duplicate exist
    private boolean checkDuplicate(ExternalChainingMapEntry<K, V> head, K key) {
        ExternalChainingMapEntry temp = head;
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    // replace old value to new value and return the old value
    private V replace(ExternalChainingMapEntry<K, V> head, K key, V val) {
        V oldValue = null;
        ExternalChainingMapEntry temp = head;
        while (temp != null) {
            if (temp.getKey().equals(key)) {
                oldValue = (V) temp.getValue();
                temp.setValue(val);
            }
            temp = temp.getNext();
        }
        return oldValue;
    }
    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (key == null) {
            throw new java.lang.IllegalArgumentException("Key is null");
        }
        int hashCode = key.hashCode();
        int compressValue = Math.abs(hashCode % table.length);
        ExternalChainingMapEntry temp = table[compressValue];
        if (temp == null) {
            throw new java.util.NoSuchElementException("Key not found");
        }
        if (temp.getKey().equals(key)) {
            V res = (V) temp.getValue();
            table[compressValue] = temp.getNext();
            return res;
        }
        while (temp.getNext() != null) {
            if (temp.getNext().getKey().equals(key)) {
                V res = (V) temp.getNext().getValue();
                temp.setNext(temp.getNext().getNext());
                return res;
            }
            temp = temp.getNext();
        }
        throw new java.util.NoSuchElementException("Key not found");
    }


    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        ExternalChainingMapEntry<K, V>[] copy = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];
        for (int i = 0; i < table.length; i += 1) {
            if (table[i] != null) {
                ExternalChainingMapEntry temp = table[i];
                helper(copy, temp);
            }
        }
        table = copy;
    }

    // helper method to copy the original array's item to new array
    private void helper(ExternalChainingMapEntry<K, V>[] copy, ExternalChainingMapEntry<K, V> head) {
        ExternalChainingMapEntry temp = head;
        while (temp != null) {
            Object key = temp.getKey();
            Object val = temp.getValue();
            int hashCode = key.hashCode();
            int compressValue = Math.abs(hashCode % copy.length);
            if (copy[compressValue] == null) {
                copy[compressValue] = new ExternalChainingMapEntry<>((K) key, (V) val);
            } else {
                copy[compressValue] = new ExternalChainingMapEntry<>((K) key, (V) val, copy[compressValue]);
            }
            temp = temp.getNext();
        }
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    // to print the whole map
    private void printMap() {
        for (ExternalChainingMapEntry entry : table) {
            if (entry != null) {
                ExternalChainingMapEntry temp = entry;
                while (temp != null) {
                    System.out.print("(" + temp.getKey() + "," + temp.getValue() + ") ");
                    temp = temp.getNext();
                }
            } else {
                System.out.print(entry + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ExternalChainingHashMap<Integer, Integer> map = new ExternalChainingHashMap<>();
        map.put(1, 1);
        map.printMap();
        map.put(14, 2);
        map.printMap();
        map.remove(1);
        map.put(27, 2);
        map.put(40, 5);
        map.remove(14);
        map.printMap();
    }
}
