package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;

public class StringListImpl implements StringList {
    private final String[] storage;
    private int size;

    public StringListImpl() {
        storage = new  String[10];
    }

    public StringListImpl(int initSize) {
        storage = new String[initSize];
    }

    private String[] getInitSize(String[] initSize) {
        return initSize;
    }


    @Override
    public String add(String item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        validateSize();
        validateItem(item);
        validateIndex(index);
        if (index == size){
            storage[size++] = item;
            return item;
        }
        System.arraycopy( storage, index, storage,index + 1 , size - index);
        storage[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public String remove(int index) {
        validateIndex(index);
        String item = storage [index];
        if (index != size){
            System.arraycopy( storage, index + 1, storage,index, size - index);
        }
        size--;
        return item;


    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            String s = storage[i];
            if ( s.equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size; i >= 0; i--) {
            String s = storage[i];
            if ( s.equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        validateIndex(index);
        return storage[index];
    }

    // ************ Далее идут вспомогательне методы **********

    @Override
    public boolean equals(StringList otherList) {

        return Arrays.equals(this.toArray(),otherList.toArray());
    }

    @Override
    public int size() {
        return size ;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
     size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(storage,size);
    }

    // ********* нужно реализовать 3 дополнительных метода используются в основных выше **********
    // 1-метод. проверка индекса, что он не выходит за границы,
    // 2-метод. объект не равен null,
    // 3-метод. проверяет size на предмет свободных ячеек, т.е. не равен repository)

    private void validateItem (String item){ // проверка на null объекта
        if (item == null){
            throw new NullItemException();
        }
    }

    private void validateSize (){ // проверка size
        if (size == storage.length){
            throw new FullRepositoryException();
        }
    }

    private void validateIndex (int index){ // проверка индекса
        if (index < 0 || index > size){
            throw new InvalidIndexException();
        }
    }

}
