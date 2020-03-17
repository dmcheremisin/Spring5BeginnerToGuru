package com.example.jdbc.template.repository;

import com.example.jdbc.template.entities.User;
import com.example.jdbc.template.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class UserRepository {

    private UserMapper userMapper = new UserMapper();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from users", userMapper);
    }

    public User getUserById(Integer id) {
        return jdbcTemplate.queryForObject("select * from users where id = ?", new Object[]{id}, userMapper);
    }

    public Integer createUser(User user) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into users(first_name, last_name) values (?, ?)");
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            return ps;
        }, keyHolder);

        return (Integer) keyHolder.getKey();
    }

    public void updateUser(Integer id, User user) {
        jdbcTemplate.update("update users set first_name = ? , last_name = ? where id = ? ", user.getFirstName(), user.getLastName(), id);
    }

    public void deleteUser(Integer id) {
        jdbcTemplate.update("delete from users where id = ? ", id);
    }
}
