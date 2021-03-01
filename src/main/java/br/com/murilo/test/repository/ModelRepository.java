package br.com.murilo.test.repository;

import org.springframework.stereotype.Repository;

import br.com.murilo.test.abstraction.BaseRepository;
import br.com.murilo.test.entity.Model;

@Repository
public interface ModelRepository extends BaseRepository<Model> {
    
}
