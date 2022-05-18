import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue q1 = new Queue(3);
        System.out.print("Введите элемент(ы): ");
        while (true){
            char f = sc.next().charAt(0);
            if (f == 's') {
                break;
            } else if (f == 'g') {
                q1.remove();
            } else {
                q1.put(f);
            }
            q1.display();
        }
        q1.display();
    }
}

class Queue {
    private char[] arr;
    private int putId, getId, firstId, lastId;

    public Queue(){
        arr = new char[10];
    }

    public Queue(int capacity){
        arr = new char[capacity];
    }

    public void put(char ch) {
        if (putId >= arr.length && getId == 0) {
            char[] dop = new char[arr.length * 2];
            for (int i = 0; i < arr.length; i++) {
                dop[i] = arr[i];
            }
            arr = dop;
            arr[putId] = ch;
            lastId = putId;
            putId++;

        } else if (putId >= arr.length) {
            putId = 0;
            arr[putId] = ch;
            lastId = putId;
            putId++;
        } else if (putId == getId - 1) {
            char[] dop2 = new char[arr.length * 2];
            for (int i = 0; i <= lastId; i++) {
                dop2[i] = arr[i];
            }
            for (int i = firstId; i < arr.length; i++) {
                dop2[i + arr.length] = arr[i];
            }
            firstId = firstId + arr.length;
            getId = firstId;
            arr = dop2;
            arr[putId] = ch;
            lastId = putId;
            putId++;

        } else {
            arr[putId] = ch;
            lastId = putId;
            putId++;
        }
    }

    public void remove() {
        if (getId == putId) {
            System.out.println("Empty");
        } else if (getId >= arr.length - 1) {
            char p = arr[getId];
            getId++;
            char[] test = new char[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if (i != getId - 1) {
                    test[i] = arr[i];
                }
            }
            getId = 0;
            firstId = getId;
            arr = test;
        } else {
            char p = arr[getId];
            getId++;
            firstId = getId;
            char[] test = new char[arr.length];
            for (int i = 0; i < arr.length; i++) {
                if (i != getId - 1) {
                    test[i] = arr[i];
                }
            }
            arr = test;
        }
    }

    public char get(int index) {
        return arr[index];
    }

    public void setValue(int index, char value) {
        arr[index] = value;
    }

    public void display() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " " );
        }
        System.out.println("\n first id: " + firstId + " lastId: " + lastId + " getId: " + getId + " putId: " + putId + " arr.length: " + arr.length);
    }
}