package org.kurron.spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
interface AlphaRepository extends CrudRepository<Alpha, Long> {}
