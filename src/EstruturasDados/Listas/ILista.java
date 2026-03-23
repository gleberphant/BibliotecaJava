package EstruturasDados.Listas;

public interface ILista<T> {

    public boolean Inserir(T dado);

    public boolean InserirInicio(T dado);

    public boolean InserirMeio(T dado);

    public boolean InserirFim(T dado);

    public NoLista<T> PrimeiroNo();

    public NoLista<T> ProximoNo();

    public NoLista<T> UltimoNo();

    public boolean ReiniciarPonteiro();

    public T GetPrimeiro();

    public T Get();


    public T Get(int i);

    public void Set(int i, T dado);

     public void Remover(int i);

    public T GetUltimoItem();

    public int Tamanho();

    public String toString();
}
