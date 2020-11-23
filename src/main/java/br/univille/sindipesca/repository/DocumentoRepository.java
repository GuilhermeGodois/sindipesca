package br.univille.sindipesca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.sindipesca.model.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Long> {

}