package com.thanhtuyen.webbook.services.impl;

import com.thanhtuyen.webbook.dto.UserDTO;
import com.thanhtuyen.webbook.entities.Role;
import com.thanhtuyen.webbook.entities.User;
import com.thanhtuyen.webbook.mapper.UserMapper;
import com.thanhtuyen.webbook.repositories.RoleRepository;
import com.thanhtuyen.webbook.repositories.UserRepository;
import com.thanhtuyen.webbook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(UserDTO userDTO) {
        User userExists = userRepository.findByUsername(userDTO.getUsername());
        if (userExists != null){
            return null;
        }

        User userRegister = userMapper.toEntity(userDTO);
        userRegister.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRegister.setName("Tran Thi Thanh Tuyen");
        userRegister.setPhone("0396172224");
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName("ADMIN"));
        userRegister.setRoles(roles);

        userRegister = userRepository.save(userRegister);
        return userMapper.toDTO(userRegister);
    }

    @Override
    public UserDTO findByUsername(String username) {
        return userMapper.toDTO(userRepository.findByUsername(username));
    }
}
