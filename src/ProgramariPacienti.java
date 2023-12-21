import java.util.Date;

public class ProgramariPacienti {
    private Pacient pacient;
    private Date data;
    private int ora;

    public ProgramariPacienti(Pacient pacient, Date data, int ora) {
        this.pacient = pacient;
        this.data = data;
        this.ora = ora;
    }

    @Override
    public String toString() {
        return "ProgramariPacienti{" +
                "pacient=" + pacient +
                ", data=" + data +
                ", ora=" + ora +
                '}';
    }

    public Pacient getPacient() {
        return pacient;
    }

    public void setPacient(Pacient pacient) {
        this.pacient = pacient;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getOra() {
        return ora;
    }

    public void setOra(int ora) {
        this.ora = ora;
    }
}
