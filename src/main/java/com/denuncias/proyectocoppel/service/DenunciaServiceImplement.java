package com.denuncias.proyectocoppel.service;
import com.denuncias.proyectocoppel.dao.DenunciaDao;
import com.denuncias.proyectocoppel.entity.Denuncia;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j //log.info("Algun mensaje")
public class DenunciaServiceImplement implements DenunciaService {

    //Variable constante
    private static final String ID_FOLIO_PARAM_NAME = "idFolio";


    @Autowired
    private DenunciaDao denunciaDao;

    /**
     * Obtiene todas las denuncias
     * @return Lista de denuncias
     */
    @Override // Indica que el método sobrescribe un método de la clase padre
    @Transactional // Indica que este método se ejecutará en una transacción de base de datos
    public List<Denuncia> findAll() {
        // El método findAll() en la clase DenunciaDao devuelve una lista de todas las denuncias en la base de datos
        return denunciaDao.findAll();
    }


    /**
     * Guarda una nueva denuncia
     * @param denuncia Denuncia a guardar
     * @return Denuncia guardada
     */
    @Override
    @Transactional
    public Denuncia save(Denuncia denuncia) {
        return denunciaDao.save(denuncia);
    }

    /**
     * Obtiene una denuncia por su ID
     * @param idFolio ID de la denuncia
     * @return Denuncia encontrada o null si no existe
     */
    @Override
    @Transactional
    public Denuncia findById(Integer idFolio) {
        return denunciaDao.findById(idFolio).orElse(null);
    }

    /**
     * Elimina una denuncia
     * @param denuncia Denuncia a eliminar
     */
    @Override
    @Transactional
    public void delete(Denuncia denuncia) {
        denunciaDao.delete(denuncia);
    }
}
