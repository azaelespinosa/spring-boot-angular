package com.chub.service;


import com.chub.exception.ResourceNotFoundException;
import com.chub.exception.ValidationException;
import com.chub.mode.BaseEntity;
import com.chub.repository.BaseRepository;
import com.chub.util.ConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T extends BaseRepository, E extends BaseEntity> {

    @Autowired
    protected T repository;

    @Autowired
    protected ConvertUtil convertUtils;

    private Class<E> clazz;

    public Class<E> clazz() {

        if (this.clazz == null) {

            Class<?> actualClass = this.getClass();

            ParameterizedType pt = (ParameterizedType) actualClass.getGenericSuperclass();

            this.clazz = (Class<E>) pt.getActualTypeArguments()[1];
        }

        return this.clazz;
    }

    public <K> K findById(long id, Class<K> clazz) throws Exception {

        Optional<E> optionalEntity = this.repository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new ResourceNotFoundException(this.clazz().getName(),"id" , id);
        }

        E entity = optionalEntity.get();
        K optionalDTO = convertUtils.convert(entity, clazz);

        return optionalDTO;
    }

    public <K> K findByUuid(String uuid, Class<K> clazz) throws Exception {

        Optional<E> optionalEntity = this.repository.findByUuid(uuid);

        if (!optionalEntity.isPresent()) {
            throw new ResourceNotFoundException(this.clazz().getName(),"uuid" , uuid);
        }

        E entity = optionalEntity.get();
        K dto = convertUtils.convert(entity, clazz);

        return dto;
    }

    public <K> List<K> findAll(Class<K> clazz) {

        List<E> all = repository.findAll();

        List<K> list = convertUtils.convert(all, clazz);

        return list;
    }

    public <K, D> D create(K dto, Class<D> clazz)  {

        E entity = convertUtils.convert(dto, this.clazz());
        D outDTO = convertUtils.convert(repository.save(entity), clazz);

        return outDTO;
    }

    public <K, D> D update(long id, K dto, Class<D> clazz) {

        Optional<E> optionalEntity = this.repository.findById(id);

        if (!optionalEntity.isPresent()) {
              throw new ResourceNotFoundException(this.clazz().getName(),"id" , id);
        }

        E entity = optionalEntity.get();

        convertUtils.map(dto, entity);
        repository.save(entity);

        D outDTO = convertUtils.convert(entity, clazz);

        return outDTO;
    }

    public <K> K delete(long id, Class<K> clazz) {

        Optional<E> optionalEntity = this.repository.findById(id);

        if (!optionalEntity.isPresent()) {
            throw new ResourceNotFoundException(this.clazz().getName(),"id" , id);
        }

        E entity = optionalEntity.get();
        entity.setSoftDelete(true);

        repository.save(entity);

        K dto = clazz != null ?
                convertUtils.convert(entity, clazz) : null;

        return dto;
    }

    public <K> K delete(String uuid, Class<K> clazz)  {

        Optional<E> optionalEntity = this.repository.findByUuid(uuid);

        if (!optionalEntity.isPresent()) {
            throw new ResourceNotFoundException(this.clazz().getName(),"uuid" , uuid);
        }

        E entity = optionalEntity.get();
        entity.setSoftDelete(true);

        repository.save(entity);

        K dto = clazz != null ?
                convertUtils.convert(entity, clazz) : null;

        return dto;
    }

    public void delete(long id)  {
        this.delete(id, null);
    }

    public void delete(String uuid)  {
        this.delete(uuid, null);
    }

    public ConvertUtil getConvertUtils() {
        return convertUtils;
    }

}