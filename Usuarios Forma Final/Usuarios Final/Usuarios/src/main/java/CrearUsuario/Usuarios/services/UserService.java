package CrearUsuario.Usuarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CrearUsuario.Usuarios.model.User;
import CrearUsuario.Usuarios.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<User> getAllUsersOrderedByEmail() {
        return userRepository.findAllByOrderByEmailAsc();
    }
    public void deleteByEmail(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new RuntimeException("email no existe");
        }
        userRepository.deleteByEmail(email);
    }
     public User createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email ya está en uso");
        }
        return userRepository.save(user);
    }
    public User updateUserByEmail(String email, User updatedUser) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con email: " + email));


        existingUser.setNombre(updatedUser.getNombre());
        existingUser.setContrasena(updatedUser.getContrasena());
        existingUser.setRol(updatedUser.getRol());
        existingUser.setEmail(updatedUser.getEmail());



        return userRepository.save(existingUser);
    }
}


