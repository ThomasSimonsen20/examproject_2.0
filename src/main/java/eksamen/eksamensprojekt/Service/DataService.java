package eksamen.eksamensprojekt.Service;

import eksamen.eksamensprojekt.Model.Kommune;
import eksamen.eksamensprojekt.Model.Sogn;
import eksamen.eksamensprojekt.Repository.KommuneRepository;
import eksamen.eksamensprojekt.Repository.SogneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataService {

    @Autowired
    KommuneRepository kommuneRepository;

    @Autowired
    SogneRepository sogneRepository;

    public ResponseEntity<String> create (Sogn sogn) {

        for (Kommune k : kommuneRepository.findAll()) {
            if (k.getNavn().equals(sogn.getKommune().getNavn())) {
                Kommune kommune = kommuneRepository.findById(k.getId()).get();
                kommuneRepository.save(kommune);
                sogn.setKommune(kommune);
            }
        }
        sogneRepository.save(sogn);

        return ResponseEntity.status(HttpStatus.CREATED).header("Location", "/sogne" + sogn.getId())
                .body("{'Msg': 'Sogn created'}");
    }

    public ResponseEntity<String> update(Long id, Sogn sogn) {
        Optional<Sogn> optionalSogne = sogneRepository.findById(id);
        if (!optionalSogne.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'msg' : 'Sogn " + id + " not found'");
        }

        for (Kommune k : kommuneRepository.findAll()) {
            if (k.getNavn().equals(sogn.getKommune().getNavn())) {
                Kommune kommune = kommuneRepository.findById(k.getId()).get();
                kommuneRepository.save(kommune);
                sogn.setKommune(kommune);
            }
        }
        sogn.setId(id);
        sogneRepository.save(sogn);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("{'Sogn' : 'updated'}");
    }

    public ResponseEntity<String> delete(Long id) {
        Optional<Sogn> optionalSogne = sogneRepository.findById(id);
        if(!optionalSogne.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{'Sogn " + id + " not found'");
        }
        Sogn sogn = optionalSogne.get();

        sogn.setKommune(null);
        sogneRepository.save(sogn);
        sogneRepository.deleteById(id);

        return ResponseEntity.status(HttpStatus.OK).body("{'Sogn' : 'deleted'}");
    }

    public ResponseEntity<Optional<Sogn>> findById(Long id){
        Optional<Sogn> optionalSogn = sogneRepository.findById(id);
        if (optionalSogn.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalSogn);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(optionalSogn);
        }
    }

    public ResponseEntity<Iterable<Sogn>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(sogneRepository.findAll());
    }
}
