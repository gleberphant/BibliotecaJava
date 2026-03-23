package EstruturasDados.Listas;

public class NoLista<T> {
    public T data;
    public NoLista<T> next;

    public NoLista(T data) {
        this.data = data;
    }

    public NoLista<T> getNext() {
        return next;
    }

    public void setNext(NoLista<T> next) {
        this.next = next;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}