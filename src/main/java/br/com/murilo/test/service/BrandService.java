package br.com.murilo.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.murilo.test.abstraction.BaseRepository;
import br.com.murilo.test.abstraction.BaseService;
import br.com.murilo.test.entity.Brand;
import br.com.murilo.test.repository.BrandRepository;

@Service
public class BrandService extends BaseService<Brand> {
	
    @Autowired
	private BrandRepository brandRepository;

	@Override
	public BaseRepository<Brand> getRepository() {
		return brandRepository;
	}
}
