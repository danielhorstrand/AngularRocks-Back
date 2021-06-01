package angularrocks.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import angularrocks.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Long>{
	
	List<Grupo> findByPublished(boolean published);

	List<Grupo> findByNameContaining(String name);
}
