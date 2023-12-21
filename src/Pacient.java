import java.util.Date;
import java.util.List;

public class Pacient extends User {
    private int id;
    private String nume;
    private String dataNasterii;
    private  String telefon;
    private List<IstoricMedical> istoricMedical;
    private List<ProgramareLaMedic> programari;

    public Pacient(int id,String email, String password, String nume, String dataNasterii, String telefon) {
        super(email, password);
        this.nume = nume;
        this.dataNasterii = dataNasterii;
        this.telefon=telefon;
        this.id=id;
    }
    public Pacient(String email, String password, String nume, String dataNasterii, String telefon) {
        super(email, password);
        this.nume = nume;
        this.dataNasterii = dataNasterii;
        this.telefon=telefon;
    }

    public Pacient(String email, String password, String nume, String dataNasterii, String telefon,List<IstoricMedical> istoricMedical, List<ProgramareLaMedic> programari) {
        super(email, password);
        this.nume = nume;
        this.dataNasterii = dataNasterii;
        this.istoricMedical = istoricMedical;
        this.programari = programari;
        this.telefon=telefon;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "nume='" + nume + '\'' +
                ", dataNasterii=" + dataNasterii +
                ", istoricMedical=" + istoricMedical +
                ", programari=" + programari +
                '}';
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDataNasterii() {
        return dataNasterii;
    }

    public void setDataNasterii(String dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public List<IstoricMedical> getIstoricMedical() {
        return istoricMedical;
    }

    public void setIstoricMedical(List<IstoricMedical> istoricMedical) {
        this.istoricMedical = istoricMedical;
    }

    public List<ProgramareLaMedic> getProgramari() {
        return programari;
    }

    public void setProgramari(List<ProgramareLaMedic> programari) {
        this.programari = programari;
    }
    public String getTelefon(){return telefon;}
    public void setTelefon(String telefon){ this.telefon=telefon;}
}
