package com.projeto.sistemarh.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.projeto.sistemarh.model.Candidato;
import com.projeto.sistemarh.model.Vaga;

public interface CandidatoRepository extends CrudRepository<Candidato, String> {
    
    Iterable<Candidato>findByVaga(Vaga vaga);

    Candidato findByRg(String rg);
    Candidato findById(long id);
    List<Candidato> findByNomeCandidato(String nome);
    
}