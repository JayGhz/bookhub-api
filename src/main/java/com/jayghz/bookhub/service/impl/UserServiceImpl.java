package com.jayghz.bookhub.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import com.jayghz.bookhub.dto.AuthResponseDTO;
import com.jayghz.bookhub.dto.LoginDTO;
import com.jayghz.bookhub.dto.UserProfileDTO;
import com.jayghz.bookhub.dto.UserRegisterDTO;
import com.jayghz.bookhub.exception.*;
import com.jayghz.bookhub.mapper.UserMapper;
import com.jayghz.bookhub.model.entity.*;
import com.jayghz.bookhub.model.enums.ERole;
import com.jayghz.bookhub.repository.*;
import com.jayghz.bookhub.security.TokenProvider;
import com.jayghz.bookhub.security.UserPrincipal;
import com.jayghz.bookhub.service.UserService;

import lombok.RequiredArgsConstructor;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final AuthorRepository authorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder; // Encriptador de contraseñas
    private final UserMapper userMapper;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    @Transactional
    @Override
    public UserProfileDTO registerCustomer(UserRegisterDTO registerDTO) {
        return registerUserWithRole(registerDTO, ERole.CUSTOMER);
    }

    @Transactional
    @Override
    public UserProfileDTO registerAuthor(UserRegisterDTO registerDTO) {
        return registerUserWithRole(registerDTO, ERole.AUTHOR);
    }

    @Transactional
    @Override
    public UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        // Verificar si existe un cliente o autor con el mismo nombre y apellido
        boolean existsAsCustomer = customerRepository.existsByFirstNameAndLastName(userProfileDTO.getFirstName(), userProfileDTO.getLastName());
        boolean existsAsAuthor = authorRepository.existsByFirstNameAndLastName(userProfileDTO.getFirstName(), userProfileDTO.getLastName());

        if (existsAsAuthor || existsAsCustomer) {
            throw new BadRequestException("Ya existe un usuario con el mismo nombre y apellido");
        }
        if (user.getCustomer() != null) {
            user.getCustomer().setFirstName(userProfileDTO.getFirstName());
            user.getCustomer().setLastName(userProfileDTO.getLastName());
            user.getCustomer().setShippingAddress(userProfileDTO.getShippingAddress());

        } else if (user.getAuthor() != null) {
            user.getAuthor().setFirstName(userProfileDTO.getFirstName());
            user.getAuthor().setLastName(userProfileDTO.getLastName());
            user.getAuthor().setBio(userProfileDTO.getBio());
        }

        User updatedUser = userRepository.save(user);
        return userMapper.toUserProfileDTO(updatedUser);
        
    }

    @Transactional(readOnly = true)
    @Override
    public UserProfileDTO getUserProfileById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
        return userMapper.toUserProfileDTO(user);
    }

    // Método genérico para registrar un usuario con un rol específico
    private UserProfileDTO registerUserWithRole(UserRegisterDTO registerDTO, ERole roleEnum) {

        // Verificar si el email ya está registrado o si ya existe un usuario con el mismo nombre y apellido
        boolean emailExists = userRepository.existsByEmail(registerDTO.getEmail());
        boolean existsAsCustomer = customerRepository.existsByFirstNameAndLastName(registerDTO.getFirstName(),
                registerDTO.getLastName());
        boolean existsAsAuthor = authorRepository.existsByFirstNameAndLastName(registerDTO.getFirstName(),
                registerDTO.getLastName());

        if (emailExists) {
            throw new BadRequestException("El email ya está registrado");
        }

        if (existsAsCustomer || existsAsAuthor) {
            throw new BadRequestException("Ya existe un usuario con el mismo nombre y apellido");
        }

        // Asignar el rol del usuario
        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new RoleNotFoundException("Rol no encontrado"));

        // Cifrar la contraseña
        registerDTO.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        // Convertir el DTO a una entidad User
        User user = userMapper.toUserEntity(registerDTO);
        user.setRole(role); // Asignar el rol al usuario

        // Asignar la entidad específica basada en el rol
        if (roleEnum == ERole.CUSTOMER) {
            // Crear un cliente
            Customer customer = new Customer();
            customer.setFirstName(registerDTO.getFirstName());
            customer.setLastName(registerDTO.getLastName());
            customer.setShippingAddress(registerDTO.getShippingAddress());
            customer.setCreatedAt(LocalDateTime.now());
            customer.setUser(user); // Enlazar el cliente con el usuario
            user.setCustomer(customer);

        } else if (roleEnum == ERole.AUTHOR) {
            // Crear un autor
            Author author = new Author();
            author.setFirstName(registerDTO.getFirstName());
            author.setLastName(registerDTO.getLastName());
            author.setBio(registerDTO.getBio());
            author.setCreatedAt(LocalDateTime.now());
            author.setUser(user); // Enlazar el autor con el usuario
            user.setAuthor(author);
        }

        User savedUser = userRepository.save(user);
        return userMapper.toUserProfileDTO(savedUser);
    }

    @Override
    public AuthResponseDTO login(LoginDTO loginDTO) {
        // Autenticar al usuario usando AuthenticationManager
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
       
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        // Generar un token JWT
        String token = tokenProvider.createAccessToken(authentication);

        AuthResponseDTO authResponseDTO = userMapper.toAuthResponseDTO(user, token);

        return authResponseDTO;
    }
}
