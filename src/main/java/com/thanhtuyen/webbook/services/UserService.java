package com.thanhtuyen.webbook.services;

import com.thanhtuyen.webbook.dto.UserDTO;

public interface UserService {
    UserDTO register(UserDTO userDTO);

    UserDTO findByUsername(String username);
}
