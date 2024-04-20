package org.xproce.revormclass.dao.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.xproce.revormclass.dao.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findByDescriptionContains(String keyword);
    Page<Product> findByDescriptionContains(String keyword, Pageable pageable);

}
