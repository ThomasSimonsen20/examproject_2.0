package eksamen.eksamensprojekt.Service;

import eksamen.eksamensprojekt.Model.Sogn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class DataServiceTest {

    @Autowired
    DataService dataService;


    @Test
    void create() {
        Sogn sogn = dataService.findById(2L).getBody().get();

        ResponseEntity<String> test = dataService.create(sogn);

        assertEquals("{'Msg': 'Sogn created'}", test.getBody());
    }

    @Test
    void update() {
        ResponseEntity<Optional<Sogn>> test = dataService.findById(10L);
        test.getBody().get().setNavn("TestNavn");

        dataService.update(10L, test.getBody().get());

        assertEquals("TestNavn", dataService.findById(10L).getBody().get().getNavn());
    }

    @Test
    void update2() {
        ResponseEntity<String> test = dataService.update(10L, dataService.findById(10L).getBody().get());

        assertEquals("{'Sogn' : 'updated'}", test.getBody());
    }

    @Test
    void delete() {
        ResponseEntity<String> test = dataService.delete(5L);

        assertEquals("{'Sogn' : 'deleted'}", test.getBody());
    }

    @Test
    void findById() {
        ResponseEntity<Optional<Sogn>> test = dataService.findById(10L);

        assertEquals(10, test.getBody().get().getId());
    }

    @Test
    void findAll() {
        ResponseEntity<Iterable<Sogn>> test = dataService.findAll();

        assertEquals(12, ((Collection<?>) test.getBody()).size());
    }
}