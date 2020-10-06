/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lists;

/**
 *
 * @author samaniw
 * @param <T>
 */
public class CircularDoubleLinkedList<T extends Number & Comparable> implements Ilist<T> {

    DoubleNode<T> head;

    public CircularDoubleLinkedList() {
        head = null;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void add(T d) {
        DoubleNode<T> newNode= new DoubleNode<>(d);
        if(isEmpty()){
            head= newNode;
            newNode.setNextNode(newNode);
            newNode.setPreviousNode(newNode);
        }else{
            newNode.setPreviousNode(head.getPreviousNode());
            newNode.setNextNode(head);
            head.getPreviousNode().setNextNode(newNode);
            head.setPreviousNode(newNode);
            head=newNode;
            
        }
    }

    @Override
    public void addLast(T d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOrdered(T d) {
       //Agregar datos ordenados sin repetir valor
       //(si el dato ya se encuentra en la lista, no ingresarlo y mostrar advertencia).
       if (isEmpty() || head.getData().compareTo(d) > 0) {
            add(d);
        }else{
            if (verificarSielDatoExiste(d)) {
                System.out.println("El dato ya existe");
            }else if(head.getPreviousNode().getData().compareTo(d) < 0){
                addLast(d);   
            }else{
                DoubleNode<T> current = head;
                while (current.getData().compareTo(d) < 0) {
                    current = current.getNextNode();
                }
                DoubleNode<T> preceding = current.getPreviousNode();
                DoubleNode<T> newNode = new DoubleNode<>(preceding, d, current);
                preceding.setNextNode(newNode);
                current.setPreviousNode(newNode);
            }   
        }
    }
    private boolean verificarSielDatoExiste(T d) {
        boolean x = false;
        DoubleNode<T> current = head;
        while (current.getNextNode() != head) {
            if (current.getData() == d) {
                x = true;
            }
            current = current.getNextNode();
        }
        return x;
    }
    
    
    @Override
    public void delete() {
        //Eliminar el primer dato.
        if (isEmpty()) {
            System.out.println("La lista está vacía");
        }else{
            head.getPreviousNode().setNextNode(head.getNextNode());
            head.getNextNode().setPreviousNode(head.getPreviousNode());
            head = head.getNextNode();
        }        
    }

    @Override
    public void deleteLast() {
        //Eliminar el último dato.
        if (isEmpty()){
            System.out.println("La lista está vacía");
        }else{
            head.getPreviousNode().getPreviousNode().setNextNode(head);
            head.setPreviousNode(head.getPreviousNode().getPreviousNode());
        }        
    }
/**
 * Mostrar los datos de forma descendente.
 * @return LIsta de datos en orden descendente
 */
    public String showDataDesc(){
        String data = "";
        if (isEmpty()) {
            data = "La lista está vacia";
            return data;
        }else{
            DoubleNode<T> tail = head.getPreviousNode();
            for (DoubleNode<T> i = tail; i != head; i = i.getPreviousNode()) {
                data += i.getData() + " ";
            }
            return data += head.getData();
        }
    }
    
    @Override
    public String showData() {
               if (isEmpty()) {
            return "Lista vacia";
        } else {
            String data = "";
            DoubleNode<T> tail = head.getPreviousNode();
            for (DoubleNode<T> i = head; i != tail; i = i.getNextNode()) {
                data += i.getData() + " ";
            }
            return data += tail.getData();

        }
    }

}
