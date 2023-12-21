import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;

import static javax.management.remote.JMXConnectorFactory.connect;


public class Main {

    public class DatabaseManager {
        private static Connection conn = null;
        private static final String URL = "jdbc:mysql://localhost:3308/proiect_P3";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "";

        public static Connection connect() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Conexiunea la baza de date a fost realizată cu succes!");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
            return conn;
        }

        public static void disconnect() {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Conexiunea la baza de date a fost închisă.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private static void adaugaMedic() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceți numele medicului:");
        String numeMedic = scanner.nextLine();

        System.out.println("Introduceți specializarea medicului:");
        String specializareMedic = scanner.nextLine();


        List<ZiLucru> orarMedic = new ArrayList<>();
        boolean adaugaAltaZi = true;
        while (adaugaAltaZi) {
            System.out.println("Introduceți ziua (ex. Luni, Marti, etc.):");
            String zi = scanner.nextLine();

            System.out.println("Introduceți ora de început (format 24h):");
            int oraInceput = Integer.parseInt(scanner.nextLine());

            System.out.println("Introduceți ora de sfârșit (format 24h):");
            int oraSfarsit = Integer.parseInt(scanner.nextLine());

            ZiLucru ziLucru = new ZiLucru(zi, oraInceput, oraSfarsit);
            orarMedic.add(ziLucru);

            System.out.println("Doriți să adăugați o altă zi? (Da/Nu)");
            String raspuns = scanner.nextLine();
            adaugaAltaZi = raspuns.equalsIgnoreCase("Da");
        }


        Medic medic = new Medic(numeMedic, specializareMedic, orarMedic);


        inserareMedicInBazaDeDate(medic);

        scanner.close();
        meniu();
    }

    public static void inserareMedicInBazaDeDate(Medic medic) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DatabaseManager.connect();

            String insertMedicQuery = "INSERT INTO medici (name, specializare) VALUES (?, ?)";
            pstmt = conn.prepareStatement(insertMedicQuery);
            pstmt.setString(1, medic.getNume());
            pstmt.setString(2, medic.getSpecializare());
            pstmt.executeUpdate();

            String selectIdQuery = "SELECT LAST_INSERT_ID() AS last_id FROM medici";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectIdQuery);
            int medicId = 0;
            if (rs.next()) {
                medicId = rs.getInt("last_id");
            }

            // Inserare în tabelul orar_medici
            String insertOrarQuery = "INSERT INTO orar_medici (id_medic, zi, ora_inceput, ora_sfarsit) VALUES (?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertOrarQuery);
            for (ZiLucru ziLucru : medic.getOrar()) {
                pstmt.setInt(1, medicId);
                pstmt.setString(2, ziLucru.getZi());
                pstmt.setInt(3, ziLucru.getOraInceput());
                pstmt.setInt(4, ziLucru.getOraSfarsit());
                pstmt.executeUpdate();
            }

            System.out.println("Medicul a fost adăugat în baza de date cu succes!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                DatabaseManager.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void adaugaPacient(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceți adresa de email:");
        String emailPacient = scanner.nextLine();

        System.out.println("Introduceți o parola:");
        String parolaPacient = scanner.nextLine();

        System.out.println("Introduceți numele pacientului:");
        String numePacient = scanner.nextLine();


        System.out.println("Introduceți data nașterii (ex. DD-MM-YYYY):");
        String dataNasteriiPacient = scanner.nextLine();

        System.out.println("Introduceți numărul de telefon al pacientului:");
        String telefonPacient = scanner.nextLine();

        // Adăugare în baza de date
        Pacient pacient = new Pacient(emailPacient,parolaPacient,numePacient, dataNasteriiPacient, telefonPacient);
        inserarePacientInBazaDeDate(pacient);
        meniu();

        scanner.close();
    }

    public static void inserarePacientInBazaDeDate(Pacient pacient) {
        Connection conn = null;
        PreparedStatement pstmtUser = null;
        PreparedStatement pstmtPacienti = null;

        try {
            conn = DatabaseManager.connect();

            String insertUserQuery = "INSERT INTO user (email, password) VALUES (?, ?)";
            pstmtUser = conn.prepareStatement(insertUserQuery, Statement.RETURN_GENERATED_KEYS);
            pstmtUser.setString(1, pacient.getEmail());
            pstmtUser.setString(2, pacient.getPassword());
            pstmtUser.executeUpdate();

            ResultSet generatedKeys = pstmtUser.getGeneratedKeys();
            int userId = 0;
            if (generatedKeys.next()) {
                userId = generatedKeys.getInt(1);
            } else {
                throw new SQLException("Nu s-au generat chei pentru user.");
            }


            // Inserare în tabela pacienti
            String insertPacientiQuery = "INSERT INTO pacienti (id_user, name, telefon, data_nasterii) VALUES (?, ?, ?, ?)";
            pstmtPacienti = conn.prepareStatement(insertPacientiQuery);
            pstmtPacienti.setInt(1, userId);
            pstmtPacienti.setString(2, pacient.getNume());
            pstmtPacienti.setString(3, pacient.getTelefon());
            pstmtPacienti.setString(4, pacient.getDataNasterii());
            pstmtPacienti.executeUpdate();

            System.out.println("Pacientul a fost adăugat în baza de date cu succes!");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmtUser != null) pstmtUser.close();
                if (pstmtPacienti != null) pstmtPacienti.close();
                DatabaseManager.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void vizualizareMediciCaPacient() {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DatabaseManager.connect();
        Statement stmt = null;
        ResultSet rs = null;

        if (conn != null) {
            try {
                stmt = conn.createStatement();

                String sql = "SELECT m.name, m.specializare, o.zi, o.ora_inceput, o.ora_sfarsit " +
                        "FROM medici m JOIN orar_medici o ON m.id = o.id_medic";

                rs = stmt.executeQuery(sql);

                List<Medic> listaMedici = new ArrayList<>();
                while (rs.next()) {
                    String nume = rs.getString("name");
                    String specializare = rs.getString("specializare");
                    String zi = rs.getString("zi");
                    int oraInceput = rs.getInt("ora_inceput");
                    int oraSfarsit = rs.getInt("ora_sfarsit");

                    Medic medic = null;
                    for (Medic existingMedic : listaMedici) {
                        if (existingMedic.getNume().equals(nume)) {
                            medic = existingMedic;
                            break;
                        }
                    }

                    // Dacă medicul nu există, îl adăugăm în listă
                    if (medic == null) {
                        medic = new Medic();
                        medic.setNume(nume);
                        medic.setSpecializare(specializare);
                        medic.setOrar(new ArrayList<>());
                        listaMedici.add(medic);
                    }

                    // Adăugăm ziua în orarul medicului
                    ZiLucru ziLucru = new ZiLucru(zi, oraInceput, oraSfarsit);
                    medic.getOrar().add(ziLucru);
                }

                // Afisăm informațiile despre fiecare medic
                for (Medic medic : listaMedici) {
                    System.out.println("Nume: " + medic.getNume());
                    System.out.println("Specializare: " + medic.getSpecializare());

                    System.out.println("      Orar:" );
                    for (ZiLucru ziLucru : medic.getOrar()) {
                        System.out.println(ziLucru.getZi() + ": " +
                                ziLucru.getOraInceput() + "-" + ziLucru.getOraSfarsit());
                    }

                    System.out.println("---------------------------"); // Linie nouă între listaMedici
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                    if (stmt != null)
                        stmt.close();
                    DatabaseManager.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void vizualizareMediciCaManager(){
        Scanner scanner = new Scanner(System.in);
        Connection conn = DatabaseManager.connect();
        Statement stmt = null;
        ResultSet rs = null;

        if(conn != null){
            try{
                stmt = conn.createStatement();

                String sql = "SELECT m.id, m.name, m.specializare, u.email FROM medici m JOIN user u on m.id=u.id";

                rs=stmt.executeQuery(sql);

                while(rs.next()){
                    int ID=rs.getInt("id");
                    String email=rs.getString("email");
                    String name = rs.getString("name");
                    String specializare = rs.getString("specializare");

                    System.out.println("ID: "+ID+'\n'+"Email: "+email+'\n'+"Nume: "+
                            name+'\n'+"Specializare: "+specializare+'\n'+"-------------------------");

                }
            }catch (SQLException e){
                e.printStackTrace();
            }finally {
                try{
                    if (rs != null)
                        rs.close();
                    if (stmt != null)
                        stmt.close();
                    DatabaseManager.disconnect();
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }


    }
    private static void vizualizarePacienti() {
        Connection conn = DatabaseManager.connect();
        Statement stmt = null;
        ResultSet rs = null;

        if (conn != null) {
            try {
                stmt = conn.createStatement();

                String sql = "SELECT p.id,p.name,p.telefon,p.data_nasterii, u.email, u.password " +
                        "FROM pacienti p JOIN user u ON u.id = p.id_user";

                rs = stmt.executeQuery(sql);

                List<Pacient> listaPacienti = new ArrayList<>();
                while (rs.next()) {
                    int pacientId=rs.getInt("id");
                    String nume = rs.getString("name");
                    String email = rs.getString("email");
                    String password = rs.getString("password");
                    String telefon = rs.getString("telefon");
                    String data_nasterii = rs.getString("data_nasterii");

                    Pacient pacient=null;
                    for(Pacient existingPacient: listaPacienti){
                        if(existingPacient.getNume().equals(nume)){
                            pacient = existingPacient;
                            break;
                        }
                    }

                    if(pacient == null){
                        pacient = new Pacient(pacientId,email,password,nume,data_nasterii,telefon);
                        listaPacienti.add(pacient);
                    }


                }

                // Afisăm informațiile despre fiecare pacient
                for (Pacient pacient : listaPacienti) {
                    System.out.println("   ID:"+pacient.getID());
                    System.out.println("Nume: " + pacient.getNume());
                    System.out.println("Telefon: " + pacient.getTelefon());
                    System.out.println("Data nasterii: " + pacient.getDataNasterii());
                    System.out.println("Email: " + pacient.getEmail());
                    System.out.println("Parola: " + pacient.getPassword());
                    System.out.println("---------------------------"); // Linie nouă între listaPacienti
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null)
                        rs.close();
                    if (stmt != null)
                        stmt.close();
                    DatabaseManager.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private static void stergePacient() {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DatabaseManager.connect();
        PreparedStatement pstmtPacient = null;
        PreparedStatement pstmtUser = null;

        System.out.println("Introdu id-ul pacientului pe care dorești să-l ștergi: ");
        String id = scanner.nextLine();

        if (conn != null) {
            try {
                conn.setAutoCommit(false);


                String deletePacientQuery = "DELETE FROM pacienti WHERE id=?";
                pstmtPacient = conn.prepareStatement(deletePacientQuery);
                pstmtPacient.setString(1, id);
                pstmtPacient.executeUpdate();


                String deleteUserQuery = "DELETE FROM user WHERE id=?";
                pstmtUser = conn.prepareStatement(deleteUserQuery);
                pstmtUser.setString(1, id);
                pstmtUser.executeUpdate();

                conn.commit();

                System.out.println("Pacientul și datele asociate au fost șterse cu succes!");

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } finally {
                try {
                    if (pstmtPacient != null) pstmtPacient.close();
                    if (pstmtUser != null) pstmtUser.close();
                    conn.setAutoCommit(true);
                    DatabaseManager.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    private static void stergeMedic() {
        Scanner scanner = new Scanner(System.in);
        Connection conn = DatabaseManager.connect();
        PreparedStatement pstmtMedici = null;
        PreparedStatement pstmtOrar = null;
        PreparedStatement pstmtUser = null;

        System.out.println("Introdu id-ul medicului pe care dorești să-l ștergi: ");
        String id = scanner.nextLine();

        if (conn != null) {
            try {
                conn.setAutoCommit(false);


                String deleteOrarQuery = "DELETE FROM orar_medici WHERE id_medic=?";
                pstmtOrar = conn.prepareStatement(deleteOrarQuery);
                pstmtOrar.setString(1, id);
                pstmtOrar.executeUpdate();


                String deleteMediciQuery = "DELETE FROM medici WHERE id=?";
                pstmtMedici = conn.prepareStatement(deleteMediciQuery);
                pstmtMedici.setString(1, id);
                pstmtMedici.executeUpdate();


                String deleteUserQuery = "DELETE FROM user WHERE id=?";
                pstmtUser = conn.prepareStatement(deleteUserQuery);
                pstmtUser.setString(1, id);
                pstmtUser.executeUpdate();

                conn.commit();

                System.out.println("Medicul și datele asociate au fost șterse cu succes!");

            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            } finally {
                try {
                    if (pstmtMedici != null) pstmtMedici.close();
                    if (pstmtOrar != null) pstmtOrar.close();
                    if (pstmtUser != null) pstmtUser.close();
                    conn.setAutoCommit(true);
                    DatabaseManager.disconnect();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void meniu() {
        Scanner scanner = new Scanner(System.in);
        int optiune;

        do {
            System.out.println("\n---- Meniu ----");
            System.out.println("1. Vizualizare doctori si programul lor");
            System.out.println("2. Vizualizare pacienti si datele lor");
            System.out.println("3. Adaugare pacient");
            System.out.println("4. Adaugare doctor");
            System.out.println("5. Stergere pacient");
            System.out.println("6. Stergere doctor");
            System.out.println("7. Vizualizare doctori ca MANAGER");
            System.out.println("0. Iesire");

            System.out.print("Alegeti o optiune: ");
            optiune = scanner.nextInt();
            scanner.nextLine(); // Consumă newline left by nextInt()

            switch (optiune) {
                case 1:
                    System.out.println("Vizualizare doctori si programul lor");
                    vizualizareMediciCaPacient();
                    break;
                case 2:
                    System.out.println("2. Vizualizare pacienti si datele lor");
                    vizualizarePacienti();
                    break;
                case 3:
                    System.out.println("3. Adaugare pacient");
                    adaugaPacient();
                    break;
                case 4:
                    System.out.println("4. Adaugare doctor");
                    adaugaMedic();
                    break;
                case 5:
                    System.out.println("5. Stergere pacient");
                    stergePacient();
                    break;
                case 6:
                    System.out.println("Stergere doctor");
                    stergeMedic();
                    break;
                case 7:
                    System.out.println("Vizualizare medici ca manager");
                    vizualizareMediciCaManager();
                    break;
                case 0:
                    System.out.println("Programul s-a incheiat.");
                    break;
                default:
                    System.out.println("Optiune invalida. Va rugam sa alegeti din nou.");
                    break;
            }
        } while (optiune != 0);

        scanner.close();
    }



    public static void main(String[] args) {
        Connection conn = DatabaseManager.connect();
        meniu();

    }
}
