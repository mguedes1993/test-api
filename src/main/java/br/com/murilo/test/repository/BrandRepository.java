package br.com.murilo.test.repository;

import org.springframework.stereotype.Repository;

import br.com.murilo.test.abstraction.BaseRepository;
import br.com.murilo.test.entity.Brand;

@Repository
public interface BrandRepository extends BaseRepository<Brand> {

}
