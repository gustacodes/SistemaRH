package com.projeto.sistemarh.repository;

import org.springframework.data.repository.CrudRepository;
import com.projeto.sistemarh.model.Vaga;
import java.util.List;

public interface VagaRepository extends CrudRepository<Vaga, String> {
    
    Vaga findByCodigo(long condigo);
    List<Vaga> findByNome(String nome);

}