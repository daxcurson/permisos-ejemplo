package ar.com.strellis.permisosejemplo.dao;

import ar.com.strellis.permisosejemplo.model.User;

public interface UserDAO {
    User findByLogin(String login);
    void save(User user);
    User findById(Long userId);
    User findByEmail(String email);   
    User findByLoginOpenId(String loginOpenId);
    User findByFacebookId(Long facebookId);
}