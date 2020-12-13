package com.persistence;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.model.Reunion;

@Repository
public interface ReunionRepository extends MongoRepository<Reunion, String> {

	List<Reunion> findByAsistentesIn(String asistentes);

	Optional<Reunion> findOneByConvocante(String convocante);

	List<Reunion> findByConvocante(String convocante);

	Optional<Reunion> deleteByConvocante(String convocante);

	Optional<Reunion> deleteByHoraInicio(String horaInicio);

	Optional<Reunion> findOneByHoraInicio(String horaInicio);

}
