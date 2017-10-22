package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KecamatanMapper;
import com.example.tugas1.model.KecamatanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KecamatanServiceDatabase implements KecamatanService {
	
    @Autowired
    private KecamatanMapper kecamatanMapper;

    @Override
    public KecamatanModel selectKecamatan (String namaKecamatan)
    {
    	log.info ("select kecamatan with name {}", namaKecamatan);
    	return kecamatanMapper.selectKecamatan(namaKecamatan);
    }

    @Override
    public KecamatanModel selectKecamatanById (String idKecamatan)
    {
    	log.info ("select kecamatan with name {}", idKecamatan);
    	return kecamatanMapper.selectKecamatanById(idKecamatan);
    }    
    
	@Override
	public List<KecamatanModel> selectKecamatanFromKota(String idKota) {
    	log.info ("select kecamatan from city with id {}", idKota);
		return kecamatanMapper.selectKecamatanFromKota(idKota);
	}


         
}
