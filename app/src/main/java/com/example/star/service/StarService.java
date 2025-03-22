package com.example.star.service;


import com.example.star.dao.IDao;
import com.example.star.beans.Star;
import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        this.stars = new ArrayList<>();
    }

    // Singleton pour garantir une seule instance de StarService
    public static StarService getInstance() {
        if (instance == null)
            instance = new StarService();
        return instance;
    }

    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) { // Utilisation de getId() au lieu de getStar()
                s.setImg(o.getImg());
                s.setName(o.getName());
                s.setStar(o.getStar());
                return true;
            }
        }
        return false; // Retourner false si l'ID n'a pas été trouvé
    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }

    @Override
    public Star findById(int id) {
        for (Star s : stars) {
            if (s.getId() == id)
                return s;
        }
        return null; // Retourner null si l'élément n'est pas trouvé
    }

    @Override
    public List<Star> findAll() {
        return stars; // Retourner la liste complète des stars
    }
}

