package com.example.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tugas1.dao.KotaKabupatenMapper;
import com.example.tugas1.model.KotaKabupatenModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KotaKabupatenServiceDatabase implements KotaKabupatenService {
	
    @Autowired
    private KotaKabupatenMapper kotaKabupatenMapper;

    @Override
    public KotaKabupatenModel selectKotaKabupaten (String idKotaKabupaten)
    {
    	log.info ("select kota/kabupaten with name {}", idKotaKabupaten);
    	return kotaKabupatenMapper.selectKotaKabupaten(idKotaKabupaten);
    }

	@Override
	public List<KotaKabupatenModel> selectAllKotaKabupaten() {
        log.info ("select all kota or kabupaten");
        return kotaKabupatenMapper.selectAllKotaKabupaten();
	} 
    
}
