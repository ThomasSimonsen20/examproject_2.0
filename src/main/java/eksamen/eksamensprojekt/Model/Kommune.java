package eksamen.eksamensprojekt.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Kommune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String navn;

    private int indbyggertal = 100000;

    @JsonIgnore
    @OneToMany(mappedBy = "kommune")
    private Set<Sogn> sogne;

    public Kommune() {
    }

    public Kommune(String navn) {
        this.navn = navn;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Set<Sogn> getSogne() {
        return sogne;
    }

    public void setSogne(Set<Sogn> sogn) {
        this.sogne = sogn;
    }

    public int getIndbyggertal() {
        return indbyggertal;
    }

    public void setIndbyggertal(int indbyggertal) {
        this.indbyggertal = indbyggertal;
    }
}
