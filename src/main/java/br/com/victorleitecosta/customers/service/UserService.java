package br.com.victorleitecosta.customers.service;

import br.com.victorleitecosta.customers.exception.RegisteredUserException;
import br.com.victorleitecosta.customers.model.entity.User;
import br.com.victorleitecosta.customers.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    public User save(User user) {
        boolean exists = repository.existsByUsername(user.getUsername());
        if (exists) {
            throw new RegisteredUserException(user.getUsername());
        }
        return repository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Login não encontrado!"));
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles("USER")
                .build();
    }
}
