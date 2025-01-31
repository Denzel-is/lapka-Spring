package com.example.maestropizza.service;

import com.example.maestropizza.model.Favorite;
import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.model.User;
import com.example.maestropizza.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Override
    public List<Favorite> getFavoritesByUser(User user) {
        return favoriteRepository.findByUser(user);
    }

    @Override
    public void addFavorite(User user, Pizza pizza) {
        if (!isFavorite(user, pizza)) {
            Favorite favorite = Favorite.builder()
                    .user(user)
                    .pizza(pizza)
                    .build();
            favoriteRepository.save(favorite);
        }
    }

    @Override
    public void removeFavorite(User user, Pizza pizza) {
        favoriteRepository.deleteByUserAndPizza(user, pizza);
    }

    @Override
    public boolean isFavorite(User user, Pizza pizza) {
        return favoriteRepository.findByUserAndPizza(user, pizza).isPresent();
    }
}
