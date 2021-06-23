package eksamen.eksamensprojekt.Controller;

import eksamen.eksamensprojekt.Model.Kommune;
import eksamen.eksamensprojekt.Model.Sogn;
import eksamen.eksamensprojekt.Repository.KommuneRepository;
import eksamen.eksamensprojekt.Repository.SogneRepository;
import eksamen.eksamensprojekt.Service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class RestSognController {


    DataService dataService;

    public RestSognController(DataService dataService) {
        this.dataService = dataService;
    }

    @GetMapping("/sogne")
    public ResponseEntity<Iterable<Sogn>> findAll() {
        return dataService.findAll();
    }

    @PostMapping(value = "/sogne")
    public ResponseEntity<String> create (@RequestBody Sogn sogn) {
        return dataService.create(sogn);
        }

    @PutMapping("/sogne/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Sogn sogn) {
        return dataService.update(id, sogn);
    }

    @DeleteMapping("/sogne/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return dataService.delete(id);
    }

    @GetMapping("sogne/{id}")
    public ResponseEntity<Optional<Sogn>> findById(@PathVariable Long id) {
       return dataService.findById(id);
    }

}
