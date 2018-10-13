package LimpBiscuit.Demo.Controller;

import LimpBiscuit.Demo.Entities.Routine;
import LimpBiscuit.Demo.Repositories.RoutineRepository;
import LimpBiscuit.Demo.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoutineController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoutineRepository routineRepository;

    public List<Object> join() {
        return userRepository.join();
    }

    public List<Routine> group() {
        return routineRepository.countimine();
    }
}
