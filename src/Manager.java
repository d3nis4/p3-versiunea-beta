import java.util.List;

public class Manager extends User{
    private String nume;

    private List<Pacient> listaPacienti;
    private List<Medic> listaMedici;

    public Manager( String email, String password, String nume, List<Pacient> listaPacienti, List<Medic> listaMedici) {
        super(email, password);
        this.nume = nume;
        this.listaPacienti = listaPacienti;
        this.listaMedici = listaMedici;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Pacient> getListaPacienti() {
        return listaPacienti;
    }

    public void setListaPacienti(List<Pacient> listaPacienti) {
        this.listaPacienti = listaPacienti;
    }

    public List<Medic> getListaMedici() {
        return listaMedici;
    }

    public void setListaMedici(List<Medic> listaMedici) {
        this.listaMedici = listaMedici;
    }
}


