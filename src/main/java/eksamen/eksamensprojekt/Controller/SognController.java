package eksamen.eksamensprojekt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SognController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
