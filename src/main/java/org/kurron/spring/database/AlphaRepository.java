package org.kurron.spring.database;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface AlphaRepository extends ListCrudRepository<Alpha, Long> {

    // this query will be generated at build time by Spring Data JDBC AOT
    List<Alpha> findByName(String name);
}
