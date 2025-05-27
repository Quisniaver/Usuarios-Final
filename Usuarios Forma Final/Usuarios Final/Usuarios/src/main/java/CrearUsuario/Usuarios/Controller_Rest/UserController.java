package CrearUsuario.Usuarios.Controller_Rest;

import CrearUsuario.Usuarios.model.User;
import CrearUsuario.Usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/by-email/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/all-by-email")
    public ResponseEntity<List<User>> getAllUsersOrderedByEmail() {
        return ResponseEntity.ok(userService.getAllUsersOrderedByEmail());
    }

    @DeleteMapping("/by-email/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email) {
        try {
            userService.deleteByEmail(email);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/by-email/{email}")
    public ResponseEntity<User> updateUserByEmail(@PathVariable String email, @RequestBody User updatedUser) {
        try {
            User user = userService.updateUserByEmail(email, updatedUser);
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}