package org.example;

import java.util.Arrays;

public class IntegerListImpl implements IntegerList {
    private final Integer[] storage;
    private int size;

    public IntegerListImpl() {
        storage = new  Integer[10];
    }

    public IntegerListImpl(int initSize) {
        storage = new Integer[initSize];
    }

    private Integer[] getInitSize(Integer[] initSize) {
        return initSize;
    }


    @Override
    public Integer add(Integer item) {
        validateSize();
        validateItem(item);
        storage[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
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
    public Integer set(int index, Integer item) {
        validateIndex(index);
        validateItem(item);
        storage[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        validateItem(item);
        int index = indexOf(item);
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        validateIndex(index);
        Integer item = storage [index];
        if (index != size){
            System.arraycopy( storage, index + 1, storage,index, size - index);
        }
        size--;
        return item;


    }

    @Override
    public boolean contains(Integer item) {
        Integer[] storageCopy = toArray();
        sort(storageCopy);
        return binarySearch(storageCopy,item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            Integer s = storage[i];
            if ( s.equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size; i >= 0; i--) {
            Integer s = storage[i];
            if ( s.equals(item)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        validateIndex(index);
        return storage[index];
    }

    // ************ Далее идут вспомогательне методы **********

    @Override
    public boolean equals(IntegerList otherList) {

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
    public Integer[] toArray() {
        return Arrays.copyOf(storage,size);
    }

    // ********* нужно реализовать 3 дополнительных метода используются в основных выше **********
    // 1-метод. проверка индекса, что он не выходит за границы,
    // 2-метод. объект не равен null,
    // 3-метод. проверяет size на предмет свободных ячеек, т.е. не равен repository)

    private void validateItem (Integer item){ // проверка на null объекта
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
    private void sort (Integer[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }
    private boolean binarySearch (Integer[] arr, Integer item){
        int min = 0;
        int max = arr.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == arr[mid]) {
                return true;
            }
            if (item < arr[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
