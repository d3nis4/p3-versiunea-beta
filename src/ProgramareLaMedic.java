import java.util.Date;

public class ProgramareLaMedic {
    private Date dataProgramare;
    private String oraProgramare;
    private Medic medicProgramare;

    public ProgramareLaMedic(Date dataProgramare, String oraProgramare, Medic medicProgramare) {
        this.dataProgramare = dataProgramare;
        this.oraProgramare = oraProgramare;
        this.medicProgramare = medicProgramare;
    }

    @Override
    public String toString() {
        return "Programare{" +
                "dataProgramare=" + dataProgramare +
                ", oraProgramare='" + oraProgramare + '\'' +
                ", medicProgramare=" + medicProgramare +
                '}';
    }

    public Date getDataProgramare() {
        return dataProgramare;
    }

    public void setDataProgramare(Date dataProgramare) {
        this.dataProgramare = dataProgramare;
    }

    public String getOraProgramare() {
        return oraProgramare;
    }

    public void setOraProgramare(String oraProgramare) {
        this.oraProgramare = oraProgramare;
    }

    public Medic getMedicProgramare() {
        return medicProgramare;
    }

    public void setMedicProgramare(Medic medicProgramare) {
        this.medicProgramare = medicProgramare;
    }
}
