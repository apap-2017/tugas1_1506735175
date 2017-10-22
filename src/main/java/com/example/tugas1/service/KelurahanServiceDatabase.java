package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KelurahanMapper;
import com.example.tugas1.model.KelurahanModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KelurahanServiceDatabase implements KelurahanService {
	
    @Autowired
    private KelurahanMapper kelurahanMapper;

    @Override
    public KelurahanModel selectKelurahan (String namaKelurahan)
    {
    	log.info ("select kelurahan with name {}", namaKelurahan);
    	return kelurahanMapper.selectKelurahan(namaKelurahan);
    }

    @Override
    public KelurahanModel selectKelurahanById (String idKelurahan)
    {
    	log.info ("select kelurahan with id {}", idKelurahan);
    	return kelurahanMapper.selectKelurahanById(idKelurahan);
    }    
    
	@Override
	public List<KelurahanModel> selectKelurahanFromKecamatan(String idKecamatan) {
    	log.info ("select kelurahan from kecamatan {}", idKecamatan);
		return kelurahanMapper.selectKelurahanFromKecamatan(idKecamatan);
	}

         
}
