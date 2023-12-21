import java.util.List;

public class Medic /*extends User*/{
    private String nume;
    private String specializare;
    private List<ZiLucru> orar;

    private List<ProgramariPacienti> programariPacienti;

    public Medic(){}
    /*public Medic(String username, String email, String password, String nume, String specializare,List<ZiLucru> orar, List<ProgramariPacienti> programariPacienti) {
        super(username, email, password);
        this.nume = nume;
        this.orar = orar;
        this.programariPacienti = programariPacienti;
        this.specializare=specializare;
    }*/
    public Medic(String nume,String specializare,List<ZiLucru> orar){
        this.nume = nume;
        this.orar = orar;
        this.specializare=specializare;
    }


    public Medic(String nume,String specializare){
        this.nume=nume;
        this.specializare=specializare;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "nume='" + nume + '\'' +
                ", specializare='" + specializare + '\'' +
                ", orar=" + orar +
                ", programariPacienti=" + programariPacienti +
                '}';
    }



    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<ZiLucru> getOrar() {
        return orar;
    }

    public void setOrar(List<ZiLucru> orar) {
        this.orar = orar;
    }

    public List<ProgramariPacienti> getProgramariPacienti() {
        return programariPacienti;
    }

    public void setProgramariPacienti(List<ProgramariPacienti> programariPacienti) {
        this.programariPacienti = programariPacienti;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }
}
