package com.kashyap.moviereview.resource;

import com.kashyap.moviereview.dto.UserDto;
import com.kashyap.moviereview.model.Role;
import com.kashyap.moviereview.model.User;
import com.kashyap.moviereview.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/apis/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/test")
    public String test() {
        return "Hi, This is Kashyap. How are you?";
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody UserDto dto) {

        final User user = convertToUser(dto);
        final boolean isSaved = userService.save(user);

        return isSaved ? "User record is successfully saved": "User record is not saved";
    }

    private UserDto convertToUserDto(User user) {
        final UserDto dto = new UserDto();

        if (user == null) {
            return dto;
        }

        if (StringUtils.isNotBlank(user.getFirstname())) dto.setFirstname(user.getFirstname());
        if (StringUtils.isNotBlank(user.getLastname())) dto.setLastname(user.getLastname());
        if (StringUtils.isNotBlank(user.getEmail())) dto.setEmail(user.getEmail());
        if (StringUtils.isNotBlank(user.getUsername())) dto.setUsername(user.getUsername());
        if (StringUtils.isNotBlank(user.getPassword())) dto.setPassword(user.getPassword());
        if (user.getMobileNo() != 0L) dto.setMobileNo(user.getMobileNo());
        if (user.getRole() != null) dto.setRole(user.getRole());

        return dto;
    }

    private User convertToUser(UserDto dto) {
        final User user = new User();

        if (dto == null) {
            return user;
        }

        if (StringUtils.isNotBlank(dto.getFirstname())) user.setFirstname(dto.getFirstname());
        if (StringUtils.isNotBlank(dto.getLastname())) user.setLastname(dto.getLastname());
        if (StringUtils.isNotBlank(dto.getEmail())) user.setEmail(dto.getEmail());
        if (StringUtils.isNotBlank(dto.getUsername())) user.setUsername(dto.getUsername());
        if (StringUtils.isNotBlank(dto.getPassword())) user.setPassword(dto.getPassword());
        if (dto.getMobileNo() != 0L) user.setMobileNo(dto.getMobileNo());
        //TODO: get the role id from cache or DB
        if (StringUtils.isNotBlank(dto.getRole())) user.setRole(dto.getRole());

        return user;
    }
}
