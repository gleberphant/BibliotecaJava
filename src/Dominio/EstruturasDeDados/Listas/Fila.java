package Dominio.EstruturasDeDados.Listas;

public class Fila<T> extends Lista<T> {

    public Fila() {
        super();
    }

    // LIFO - inserir no inicio da lista, para sempre retirar o ultimo que entrou
    @Override
    public boolean Inserir(T dado) {
        lista.InserirFim(dado);
        return true;
    }

}
