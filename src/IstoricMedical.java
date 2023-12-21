public class IstoricMedical {
    private String denumire;
    private String descriere;

    public IstoricMedical(String denumire, String descriere) {
        this.denumire = denumire;
        this.descriere = descriere;
    }

    @Override
    public String toString() {
        return "  " +
                "denumire='" + denumire + '\'' +
                ", descriere='" + descriere + '\'';
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
