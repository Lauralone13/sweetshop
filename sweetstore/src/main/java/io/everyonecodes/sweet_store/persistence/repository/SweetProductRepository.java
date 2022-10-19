package io.everyonecodes.sweet_store.persistence.repository;

import io.everyonecodes.sweet_store.domain.Sweet;
import io.everyonecodes.sweet_store.domain.SweetProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SweetProductRepository extends MongoRepository<SweetProduct, String> {
	
	List<Sweet> findByTypeContaining(String keyword);
	List<Sweet> findByFlavourContaining(String keyword);
//	List<SweetProduct> findAllAndOrderByType();
}
