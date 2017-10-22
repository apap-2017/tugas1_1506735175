package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KeluargaMapper;
import com.example.tugas1.model.KeluargaModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KeluargaServiceDatabase implements KeluargaService {
	
    @Autowired
    private KeluargaMapper keluargaMapper;

    @Override
    public KeluargaModel selectKeluarga (String kkKeluarga)
    {
    	log.info ("select keluarga with id {}", kkKeluarga);
    	return keluargaMapper.selectInfoKeluarga(kkKeluarga);
    }
    
    @Override
    public KeluargaModel selectKeluargaFromId (int idKeluarga)
    {
    	log.info ("select keluarga with familyid {}", idKeluarga);
    	return keluargaMapper.selectInfoKeluargaById(idKeluarga);
    }
     
    @Override
	public void updateKeluarga (KeluargaModel keluarga) 
	{
    	log.info ("keluarga" + keluarga + "updated");
    	keluargaMapper.updateKeluarga(keluarga);
	
	}
    
    
    @Override
	public void addKeluarga (KeluargaModel keluarga) 
	{
    	log.info ("keluarga" + keluarga + "added");
    	keluargaMapper.addKeluarga(keluarga);
	
	} 
	
    
	@Override
	public List<KeluargaModel> getSimilarNKK(String partNkk) {
        log.info ("get similar nkk");
        return keluargaMapper.selectSimilarNKK(partNkk);
	}            
	
	@Override
	public int getLastIdKeluarga() {
        log.info ("select all family");
        return keluargaMapper.getLastId();
	}


}
