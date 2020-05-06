package com.jokenpo.game.service;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.model.PersistentEntity;

import java.util.List;

public interface AbstractService<ID extends Number, E extends PersistentEntity> {

    E create(E entity);

    List<E> findAll() throws NotFoundException;

    E findById(ID id) throws NotFoundException;

    void delete(ID id);

}
