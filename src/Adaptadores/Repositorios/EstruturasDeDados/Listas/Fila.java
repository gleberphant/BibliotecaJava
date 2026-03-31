package Adaptadores.Repositorios.EstruturasDeDados.Listas;

public class Fila<T> extends Lista<T> {

    public Fila() {
        super();
    }

    // FIFO - inserir no fim pq a retirada é sempre do topo
    @Override
    public boolean Inserir(T dado) {
        lista.InserirFim(dado);
        return true;
    }

}
