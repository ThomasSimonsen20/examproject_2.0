package eksamen.eksamensprojekt.Model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Sogn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int sognKode;

    private String navn;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="kommune_id")
    private Kommune kommune;

    private double smittetryk;

    private LocalDate startPaaNedlukning;

    public Sogn(int sognKode, String navn, Kommune kommune, double smittetryk, LocalDate startPaaNedlukning) {
        this.sognKode = sognKode;
        this.navn = navn;
        this.kommune = kommune;
        this.smittetryk = smittetryk;
        this.startPaaNedlukning = startPaaNedlukning;
    }

    public Sogn() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSognKode() {
        return sognKode;
    }

    public void setSognKode(int sognKode) {
        this.sognKode = sognKode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Kommune getKommune() {
        return kommune;
    }

    public void setKommune(Kommune kommune) {
        this.kommune = kommune;
    }

    public double getSmittetryk() {
        return smittetryk;
    }

    public void setSmittetryk(double smittetryk) {
        this.smittetryk = smittetryk;
    }

    public LocalDate getStartPaaNedlukning() {
        return startPaaNedlukning;
    }

    public void setStartPaaNedlukning(LocalDate startPaaNedlukning) {
        this.startPaaNedlukning = startPaaNedlukning;
    }
}
