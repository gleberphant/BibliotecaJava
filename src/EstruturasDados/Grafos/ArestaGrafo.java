package EstruturasDados.Grafos;

public class ArestaGrafo {
    public NoGrafo No1;
    public NoGrafo No2;
    public int Peso;

    public ArestaGrafo(NoGrafo no1, NoGrafo no2) {
        this.No1 = no1;
        this.No2 = no2;
        this.Peso = 1;
    }
}
