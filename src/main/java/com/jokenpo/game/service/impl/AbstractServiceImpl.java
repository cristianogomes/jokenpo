package com.jokenpo.game.service.impl;

import com.jokenpo.game.exception.NotFoundException;
import com.jokenpo.game.service.AbstractService;
import com.jokenpo.game.model.PersistentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public class AbstractServiceImpl<ID extends Number, E extends PersistentEntity, Repository extends JpaRepository<E, ID>> implements AbstractService<ID, E>  {

    @Autowired
    private Repository repository;

    @Override
    public E create(E entity) {
        return repository.save(entity);
    }

    @Override
    public List<E> findAll() throws NotFoundException {
        List<E> entities =  this.repository.findAll();

        if (entities.isEmpty()) {
            throw new NotFoundException();
        }

        return entities;
    }

    @Override
    public E findById(ID id) throws NotFoundException {
        Optional<E> entity = this.repository.findById(id);
        return entity.orElseThrow(() -> new NotFoundException());
    }

    @Override
    public void delete(ID id) {
        this.repository.deleteById(id);
    }
}
