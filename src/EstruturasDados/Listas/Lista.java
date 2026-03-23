package EstruturasDados.Listas;

public class Lista<T> implements ILista<T> {
    private NoLista<T> head;
    private NoLista<T> current;
    private int tamanho;

    public Lista() {
        this.head = null;
        this.current = this.head;
        this.tamanho = 0;
    }

    public boolean Inserir(T dado) {
        this.InserirInicio(dado);
        tamanho++;
        return true;

    }

    public boolean InserirInicio(T dado) {
        NoLista<T> novo = new NoLista<>(dado);

        if (this.head == null) {
            this.head = novo;
            return true;
        }
        
        novo.next = this.head;
        this.head = novo;

        this.current = this.head;

        return true;

    }

    // inserir no meio da lista
    public boolean InserirMeio(T dado) {

        NoLista<T> novo = new NoLista<>(dado);

        if (this.current == null)
            this.current = novo;
        else {
            novo.next = this.current.next;
            this.current.next = novo;
        }

        return true;
    }

    // inserir no final da lista
    public boolean InserirFim(T dado) {
        NoLista<T> novo = new NoLista<>(dado);
        NoLista<T> ultimo = UltimoNo();

        if (ultimo == null)
            ultimo = novo;
        else
            ultimo.next = novo;

        return true;
    }

    public NoLista<T> PrimeiroNo() {

        return this.head;

    }

    public NoLista<T> ProximoNo() {

        if (this.current == null || this.current.next == null)
            return null;
        else
            this.current = this.current.next;

        return this.current;

    }

    public NoLista<T> BuscaNo(int i) {

        if (this.head == null) {
            return null;
        }

        this.current = this.head;
        int count = 0;

        while (count < i && this.current != null) {
            this.current = this.current.next;
            count++;
        }

        return this.current;

    }

    public NoLista<T> UltimoNo() {

        if (this.head == null) {
            return null;
        }

        NoLista<T> ponteiro = this.head;

        while (ponteiro.next != null) {
            ponteiro = ponteiro.next;
        }

        return ponteiro;

    }

    public boolean ReiniciarPonteiro() {
        this.current = this.head;
        return true;

    }

    public T GetPrimeiro() {
        if (this.head == null)
            return null;
        else
            return this.head.data;
    }

    public T Get() {
        if (this.current == null)
            return null;
        else
            return this.head.data;
    }

    public void Set(int i, T dado) {

        NoLista<T> busca = BuscaNo(i);

        if (busca != null)
            busca.data = dado;

    }

    public T Get(int i) {
        NoLista<T> busca = BuscaNo(i);

        if (busca == null)
            return null;
        else
            return busca.data;
    }

    public T GetUltimo() {
        NoLista<T> ultimo = UltimoNo();

        if (ultimo == null)
            return null;
        else
            return ultimo.data;
    }

    public int Tamanho() {
        return tamanho;
    }

    public void Remover(int i) {
        if (i == 0) {
            i = 1;
        }

        NoLista<T> anterior = BuscaNo(i - 1);

        if (anterior == null || anterior.next == null)
            return;
        else
            anterior.next = anterior.next.next;

    }

    public String toString() {
        return "LISTA ENCADEADA";
    }

}
