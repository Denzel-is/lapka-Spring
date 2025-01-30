package com.example.maestropizza.service;

import com.example.maestropizza.model.Favorite;
import com.example.maestropizza.model.Pizza;
import com.example.maestropizza.model.User;
import java.util.List;

public interface FavoriteService {
    List<Favorite> getFavoritesByUser(User user);
    void addFavorite(User user, Pizza pizza);
    void removeFavorite(User user, Pizza pizza);
    boolean isFavorite(User user, Pizza pizza);
}
