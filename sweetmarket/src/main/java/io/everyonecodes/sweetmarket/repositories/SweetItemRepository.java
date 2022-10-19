package io.everyonecodes.sweetmarket.repositories;

import io.everyonecodes.sweetmarket.domain.SweetItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SweetItemRepository extends JpaRepository<SweetItem, Long> {

	Optional<SweetItem> findByProductId(String productId);
}
