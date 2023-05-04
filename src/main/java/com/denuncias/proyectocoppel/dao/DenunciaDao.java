package com.denuncias.proyectocoppel.dao;
import com.denuncias.proyectocoppel.entity.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


//definir métodos que realizan operaciones de acceso a datos en una base de datos o cualquier otro tipo de almacenamiento de datos.
@Repository
public interface DenunciaDao extends JpaRepository <Denuncia, Integer>{

}
