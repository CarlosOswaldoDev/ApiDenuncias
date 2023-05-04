package com.denuncias.proyectocoppel.service;
import com.denuncias.proyectocoppel.entity.Denuncia;
import java.util.List;

public interface DenunciaService {

    // Retorna una lista con todas las denuncias
    public List<Denuncia> findAll();

    // Guarda una denuncia y retorna el objeto guardado
    public Denuncia save(Denuncia denuncia);

    // Retorna la denuncia correspondiente al ID de folio especificado
    Denuncia findById(Integer idFolio);

    // Elimina la denuncia especificada
    public void delete(Denuncia denuncia);
}
