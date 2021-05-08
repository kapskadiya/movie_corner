package com.kashyap.moviereview.resource;

import com.kashyap.moviereview.dto.UserDto;
import com.kashyap.moviereview.model.Role;
import com.kashyap.moviereview.model.User;
import com.kashyap.moviereview.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apis/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getAll")
    public List<UserDto> getAll() {

        final List<User> users = userService.getAll();

        return users.stream().map(this::convertToUserDto).collect(Collectors.toList());
    }

    @PostMapping(value = "/save")
    public String save(@RequestBody UserDto dto) {

        final User user = convertToUser(dto);
        final boolean isSaved = userService.save(user);

        return isSaved ? "User record is successfully saved": "User record is not saved";
    }

    @PutMapping(value = "/update/{username}")
    public String update(@RequestBody UserDto dto, @PathVariable String username) {

        if(StringUtils.isBlank(username)) {
            return "update is not successful. username is not provided.";
        }

        final boolean result = userService.update(username, dto);
        return result ? "update is successful.":  "update is not successful.";
    }

    @DeleteMapping(value = "/remove/{username}")
    public String remove(@PathVariable String username) {
        if(StringUtils.isBlank(username)) {
            return "remove is not successful. username is not provided.";
        }

        final boolean result = userService.remove(username);
        return result ? "remove is successful.":  "remove is not successful.";
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
        if (user.getRole() != null) dto.setRole(user.getRole().name());

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

        if (StringUtils.isNotBlank(dto.getRole())) {
            final String role = dto.getRole().toLowerCase();
            switch (role) {
                case "admin":
                    user.setRole(Role.ADMIN);
                    break;
                default:
                    user.setRole(Role.SUBSCRIBER);
            }
        }

        return user;
    }
}
