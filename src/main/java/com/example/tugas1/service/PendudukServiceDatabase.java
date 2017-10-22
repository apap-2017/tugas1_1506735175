package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.PendudukMapper;
import com.example.tugas1.model.PendudukModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PendudukServiceDatabase implements PendudukService {
	
    @Autowired
    private PendudukMapper pendudukMapper;

    @Override
    public PendudukModel selectPenduduk (String nik)
    {
    	log.info ("select penduduk with id {}", nik);
    	return pendudukMapper.selectInfoPenduduk(nik);
    }
    
    
    @Override
	public void updatePenduduk (PendudukModel penduduk) 
	{
    	log.info ("penduduk" + penduduk + "updated");
    	pendudukMapper.updatePenduduk(penduduk);
	} 
    
    @Override
	public void addPenduduk (PendudukModel penduduk) 
	{
    	log.info ("penduduk" + penduduk + "added");
    	pendudukMapper.addPenduduk(penduduk);	
	}


	@Override
	public List<PendudukModel> selectAllPenduduk() {
        log.info ("select all students");
        return pendudukMapper.selectAllPenduduk ();
	} 
	
	@Override
	public List<PendudukModel> getSimilarNIK(String partNik) {
        log.info ("get similar nik");
        return pendudukMapper.selectSimilarNIK(partNik);
	} 
	
	public int getLastIdPenduduk() {
        log.info ("get last id penduduk");
        return pendudukMapper.getLastId();		
	}


	@Override
	public void deactivatePenduduk(String nik) {
		log.info ("deactivate penduduk");
	    pendudukMapper.deactivatePenduduk(nik);
	}


	@Override
	public List<PendudukModel> selectPendudukFromKelurahan(String idKelurahan) {
		log.info ("select penduduk form kelurahan with id {}" + idKelurahan);
		return pendudukMapper.selectPendudukFromKelurahan(idKelurahan);
	}
     
    
}
