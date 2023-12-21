public class ZiLucru {
    private String zi;
    private int oraInceput;
    private int oraSfarsit;
    public ZiLucru(String zi, int oraInceput,int oraSfarsit) {
        this.zi = zi;
        this.oraInceput = oraInceput;
        this.oraSfarsit= oraSfarsit;

    }

    @Override
    public String toString() {
        return zi+": "+oraInceput+" - "+oraSfarsit;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }

    public int getOraInceput() {
        return oraInceput;
    }

    public void setOraInceput(int oraInceput) {
        this.oraInceput = oraInceput;
    }

    public int getOraSfarsit() {
        return oraSfarsit;
    }

    public void setOraSfarsit(int oraSfarsit) {
        this.oraSfarsit = oraSfarsit;
    }
}
